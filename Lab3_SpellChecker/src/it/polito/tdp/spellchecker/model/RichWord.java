package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String txt;
	private boolean correct;
	
	public RichWord(String txt, boolean correct) {
		super();
		this.txt = txt;
		this.correct = correct;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (correct ? 1231 : 1237);
		result = prime * result + ((txt == null) ? 0 : txt.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichWord other = (RichWord) obj;
		if (correct != other.correct)
			return false;
		if (txt == null) {
			if (other.txt != null)
				return false;
		} else if (!txt.equals(other.txt))
			return false;
		return true;
	}

	/**
	 * @return the txt
	 */
	public String getTxt() {
		return txt;
	}
	/**
	 * @param txt the txt to set
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}
	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
}
