package Tools;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Resizer
{
	public static ImageIcon get_resize_icon(String path, int width, int height)
	{
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(path));
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		return new ImageIcon(img);
	}

	public static Image get_image(String path)
	{
		URL url = ClassLoader.getSystemResource(path);
		if (url != null)
		{
			ImageIcon ii = new ImageIcon(url);
			if (ii.getImage() != null)
				return ii.getImage();
		}
		return null;
	}

	public static ImageIcon get_resize_icon(Image img, int width, int height)
	{
		ImageIcon icon = new ImageIcon(img);
		Image imgage_resize = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		return new ImageIcon(imgage_resize);
	}
}
