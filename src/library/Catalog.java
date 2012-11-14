package library;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class Catalog {

	private Collection<BookTitle> titles = new HashSet<BookTitle>();

	public void addTitle(BookTitle title) {
		titles.add(title);
	}

	public Collection<BookTitle> getTitles() {
		return Collections.unmodifiableCollection(titles);
	}

}