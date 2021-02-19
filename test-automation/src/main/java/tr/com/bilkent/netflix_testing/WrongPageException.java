package tr.com.bilkent.netflix_testing;

public class WrongPageException extends IllegalStateException {
	private static final long serialVersionUID = -3928271363403938121L;

	public WrongPageException(String s) {
		super(s);
	}
}
