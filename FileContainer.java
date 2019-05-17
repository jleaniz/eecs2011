/**
 * @author Andriy
 * A very simple class that maintains information pertaining to a file container used to store some data
 *
 */
public abstract class FileContainer {
	protected String fileName;
	public abstract String getFileName();
	public abstract long getFileSize();
}