package edu.psu.sweng500.emrms.model;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class ComplexName implements Serializable {

	private String prefix;

	@Size(max=15)
	private String first;

	@Size(max=15)
	private String middle;

	@Size(max=19)
	private String last;

	private String title;
	private String genQualifier;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenQualifier() {
		return genQualifier;
	}

	public void setGenQualifier(String genQualifier) {
		this.genQualifier = genQualifier;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(first != null) {
			sb.append(first);
			sb.append(' ');
		}
		if(middle != null) {
			sb.append(middle);
			sb.append(' ');
		}
		if(last != null) {
			sb.append(last);
			sb.append(' ');
		}
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public boolean isEmpty() {
		return StringUtils.isEmpty(prefix) &&
			   StringUtils.isEmpty(first) &&
			   StringUtils.isEmpty(middle) &&
			   StringUtils.isEmpty(last);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((middle == null) ? 0 : middle.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ComplexName other = (ComplexName) obj;
		if (first == null) {
			if (other.first != null) {
				return false;
			}
		} else if (!first.equals(other.first)) {
			return false;
		}
		if (last == null) {
			if (other.last != null) {
				return false;
			}
		} else if (!last.equals(other.last)) {
			return false;
		}
		if (middle == null) {
			if (other.middle != null) {
				return false;
			}
		} else if (!middle.equals(other.middle)) {
			return false;
		}
		if (prefix == null) {
			if (other.prefix != null) {
				return false;
			}
		} else if (!prefix.equals(other.prefix)) {
			return false;
		}
		return true;
	}
}
