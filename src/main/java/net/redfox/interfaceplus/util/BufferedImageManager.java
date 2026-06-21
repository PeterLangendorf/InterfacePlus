package net.redfox.interfaceplus.util;

import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class BufferedImageManager {
	public static final BufferedImage kEmpty = new BufferedImage(1, 1, 1);

	private BufferedImageManager() {
	}

	private static final Logger LOGGER = new Logger(BufferedImageManager.class.getName());

	/**
	 * Creates a new {@link BufferedImage} containing the image provided by the
	 * {@link String} input path. If the path is invalid, returns an empty
	 * {@link BufferedImage}
	 * 
	 * @param imgPath
	 *            The path to the image in the resource folder.
	 * @return A new {@link BufferedImage} using the path provided.
	 */
	public static BufferedImage getImage(String imgPath) {
		if (!imgPath.endsWith(".png")) {
			LOGGER.warn("Image path provided did not end in .png! " + imgPath);
			return kEmpty;
		}
		if (!imgPath.startsWith("/")) {
			LOGGER.warn("Image path provided did not start in /! " + imgPath + ". Correcting...");
			imgPath = "/" + imgPath;
		}

		try {
			InputStream stream = BufferedImageManager.class.getResourceAsStream(imgPath);
			if (stream == null) {
				LOGGER.warn(
						"An image at '" + imgPath + "' was not found! Some renderable objects may appear invisible!");
			} else {
				LOGGER.info("Image at '" + imgPath + "' was successfully loaded!");
				return ImageIO.read(stream);
			}
		} catch (IOException e) {
		}
		return kEmpty;
	}

	/**
	 * Draws the specified {@link BufferedImage} to the screen. This method must be
	 * called every loop, as images are cleared between each frame update.
	 * 
	 * @param graphics
	 *            The {@link Graphics2D} object used to draw the image. Can be
	 *            obtained via {@link WindowContext#getGraphics2D()}
	 * @param image
	 *            The {@link BufferedImage} that is displayed.
	 * @param pos
	 *            The position at which the image is displayed.
	 * @param size
	 *            The legal bounds of the image being displayed.
	 */
	public static void drawImage(Graphics2D graphics, BufferedImage image, Vector2 pos, Size2 size, double zoom) {
		graphics.drawImage(image, (int) ((pos.getX() * zoom) + 0.5), (int) ((pos.getY() * zoom) + 0.5),
				(int) ((size.getWidth() * zoom) + 0.5), (int) ((size.getHeight() * zoom) + 0.5), null);
	}
}
