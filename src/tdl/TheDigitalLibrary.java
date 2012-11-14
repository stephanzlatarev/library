package tdl;

public abstract class TheDigitalLibrary {

	private static TheDigitalLibrary library;

	public static TheDigitalLibrary getLibrary() {
		return library;
	}

	/**
	 * Establishes the connection. Note that it may take a few minutes to complete
	 */
	public abstract void connect();

	/**
	 * Open a search session. It makes a short call to server to retrieve available search options
	 */
	public abstract SearchSession openSearchSession();

	public abstract SearchResults startSearch(SearchSession session, String keywords, int pageSize);

	public abstract SearchResults fetchNextPage(SearchSession session);

	public abstract void closeSearchSession(SearchSession session);

}