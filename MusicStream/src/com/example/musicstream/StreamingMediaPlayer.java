package com.example.musicstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class StreamingMediaPlayer {
	private static final int INTIAL_KB_BUFFER = 96 * 10 / 8;// assume
															// 96kbps*10secs/8bits
															// per byte
	private static final long MEDIALENGHT_IN_SECONDS = 216;// ��׼Ʒ��mp3һ����������
	private ImageButton playButton;
	private SeekBar progressBar;
	private TextView playTime;
	private long mediaLengthInKb;
	private int totalKbRead = 0;

	// ���ڸ���������
	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	
	private MediaPlayer mediaPlayer;
	private File downloadingMediaFile;
	private boolean isInterrupted;
	private Context context;
	private int counter = 0;

	public StreamingMediaPlayer(Context context, ImageButton playButton,
			SeekBar progressBar, TextView playTime) {
		this.context = context;
		this.playButton = playButton;
		this.playTime = playTime; // ���ŵĽ���ʱ��
		this.progressBar = progressBar;
	}

	/**
	 * ����һ���̣߳���������
	 */
	public void startStreaming(final String mediaUrl) throws IOException {
		Runnable r = new Runnable() {
			public void run() {
				try {
					downloadAudioIncrement(mediaUrl);
				} catch (IOException e) {
					Log.e(getClass().getName(),
							"Unable to initialize the MediaPlayer for fileUrl="
									+ mediaUrl, e);
					return;
				}
			}
		};
		new Thread(r).start();
	}

	// ���ݻ�õ�URL��ַ��������
	public void downloadAudioIncrement(String mediaUrl) throws IOException {

		URLConnection cn = new URL(mediaUrl).openConnection();
		cn.connect();
		mediaLengthInKb = cn.getContentLength() / 1000;// �����ļ�����
		InputStream stream = cn.getInputStream();
		if (stream == null) {
			Log.e(getClass().getName(),
					"Unable to create InputStream for mediaUrl:" + mediaUrl);
		}

		downloadingMediaFile = new File(context.getCacheDir(),
				"downloadingMedia.dat");

		if (downloadingMediaFile.exists()) {
			downloadingMediaFile.delete(); // ������������ɾ��
		}
		FileOutputStream out = new FileOutputStream(downloadingMediaFile);
		byte buf[] = new byte[16384];
		int totalBytesRead = 0, incrementalBytesRead = 0;
		do {
			int numread = stream.read(buf);
			if (numread <= 0)
				break;
			out.write(buf, 0, numread);
			totalBytesRead += numread;
			incrementalBytesRead += numread;
			totalKbRead = totalBytesRead / 1000; // totalKbRead��ʾ�Ѿ����ص��ļ���С
			testMediaBuffer();
			fireDataLoadUpdate();
		} while (validateNotInterrupted());
		stream.close();
		if (validateNotInterrupted()) {
			fireDataFullyLoaded();
		}
	}

	private boolean validateNotInterrupted() {
		if (isInterrupted) {
			if (mediaPlayer != null) {
				mediaPlayer.pause();
			}
			return false;
		} else {
			return true;
		}
	}

	// ���Ի�����ļ���С�Ƿ����INTIAL_KB_BUFFER��������ڵĻ��Ͳ���
	private void testMediaBuffer() {
		Runnable updater = new Runnable() {
			public void run() {
				if (mediaPlayer == null) {
					if (totalKbRead >= INTIAL_KB_BUFFER) {
						try {
							startMediaPlayer();
						} catch (Exception e) {
							Log.e(getClass().getName(),
									"Error copying buffered conent.", e);
						}
					}
				} else if (mediaPlayer.getDuration()
						- mediaPlayer.getCurrentPosition() <= 1000) {
					transferBufferToMediaPlayer();
				}
			}
		};
		handler.post(updater);
	}

	private void startMediaPlayer() {
		try {
			File bufferedFile = new File(context.getCacheDir(), "playingMedia"
					+ (counter++) + ".dat");
			moveFile(downloadingMediaFile, bufferedFile);
			Log.e(getClass().getName(),
					"Buffered File path: " + bufferedFile.getAbsolutePath());
			Log.e(getClass().getName(),
					"Buffered File length: " + bufferedFile.length() + "");
			mediaPlayer = createMediaPlayer(bufferedFile);

			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.start();
			startPlayProgressUpdater();
			playButton.setEnabled(true);

		} catch (IOException e) {
			Log.e(getClass().getName(), "Error initializing the MediaPlayer.",
					e);
		}
	}

	private MediaPlayer createMediaPlayer(File mediaFile) throws IOException {
		MediaPlayer mPlayer = new MediaPlayer();
		mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.e(getClass().getName(), "Error in MediaPlayer: (" + what
						+ ") with extra (" + extra + ")");
				return false;
			}
		});
		FileInputStream fis = new FileInputStream(mediaFile);

		mPlayer.setDataSource(fis.getFD());// �˷�������������������ļ�˵������
		mPlayer.prepare();

		return mPlayer;
	}

	private void transferBufferToMediaPlayer() {
		try {
			boolean wasPlaying = mediaPlayer.isPlaying();
			int curPosition = mediaPlayer.getCurrentPosition();

			File oldBufferedFile = new File(context.getCacheDir(),
					"playingMedia" + counter + ".dat");
			File bufferedFile = new File(context.getCacheDir(), "playingMedia"
					+ (counter++) + ".dat");

			bufferedFile.deleteOnExit();
			moveFile(downloadingMediaFile, bufferedFile);
			mediaPlayer.pause();

			mediaPlayer = createMediaPlayer(bufferedFile);
			mediaPlayer.seekTo(curPosition);

			boolean atEndOfFile = mediaPlayer.getDuration()
					- mediaPlayer.getCurrentPosition() <= 1000;
			if (wasPlaying || atEndOfFile) {
				mediaPlayer.start();
			}

			oldBufferedFile.delete();

		} catch (Exception e) {
			Log.e(getClass().getName(),
					"Error updating to newly loaded content.", e);
		}
	}

	private void fireDataLoadUpdate() {
		Runnable updater = new Runnable() {
			public void run() {
				// textStreamed.setText((totalKbRead + " Kb"));
				float loadProgress = ((float) totalKbRead / (float) mediaLengthInKb);
				progressBar.setSecondaryProgress((int) (loadProgress * 100));
			}
		};
		handler.post(updater);
	}

	private void fireDataFullyLoaded() {
		Runnable updater = new Runnable() {
			public void run() {
				transferBufferToMediaPlayer();

				downloadingMediaFile.delete();
			}
		};
		handler.post(updater);
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void startPlayProgressUpdater() {
		float progress = (((float) mediaPlayer.getCurrentPosition() / 1000) / MEDIALENGHT_IN_SECONDS);
		progressBar.setProgress((int) (progress * 100));
		int pos = mediaPlayer.getCurrentPosition();
		int min = (pos / 1000) / 60;
		int sec = (pos / 1000) % 60;
		if (sec < 10)
			playTime.setText("" + min + ":0" + sec);// �����ֲ��ŵĽ��ȣ�ת����ʱ��
		else
			playTime.setText("" + min + ":" + sec);

		if (mediaPlayer.isPlaying()) {
			Runnable notification = new Runnable() {
				public void run() {
					startPlayProgressUpdater();
				}
			};
			handler.postDelayed(notification, 1000);
		}
	}

	//
	// public void interrupt() {
	// playButton.setEnabled(false);
	// isInterrupted = true;
	// validateNotInterrupted();
	// }

	public void moveFile(File oldLocation, File newLocation) throws IOException {

		if (oldLocation.exists()) {
			BufferedInputStream reader = new BufferedInputStream(
					new FileInputStream(oldLocation));
			BufferedOutputStream writer = new BufferedOutputStream(
					new FileOutputStream(newLocation, false));
			try {
				byte[] buff = new byte[8192];
				int numChars;
				while ((numChars = reader.read(buff, 0, buff.length)) != -1) {
					writer.write(buff, 0, numChars);
				}
			} catch (IOException ex) {
				throw new IOException("IOException when transferring "
						+ oldLocation.getPath() + " to "
						+ newLocation.getPath());
			} finally {
				try {
					if (reader != null) {
						writer.close();
						reader.close();
					}
				} catch (IOException ex) {
					Log.e(getClass().getName(),
							"Error closing files when transferring "
									+ oldLocation.getPath() + " to "
									+ newLocation.getPath());
				}
			}
		} else {
			throw new IOException(
					"Old location does not exist when transferring "
							+ oldLocation.getPath() + " to "
							+ newLocation.getPath());
		}
	}

}
