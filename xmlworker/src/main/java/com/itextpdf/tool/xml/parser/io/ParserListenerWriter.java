/*
 * $Id$
 *
 * This file is part of the iText (R) project.
 * Copyright (c) 1998-2011 1T3XT BVBA
 * Authors: Balder Van Camp, Emiel Ackermann, et al.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY 1T3XT,
 * 1T3XT DISCLAIMS THE WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA, 02110-1301 USA, or download the license from the following URL:
 * http://itextpdf.com/terms-of-use/
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License,
 * a covered work must retain the producer line in every PDF that is created
 * or manipulated using iText.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the iText software without
 * disclosing the source code of your own applications.
 * These activities include: offering paid services to customers as an ASP,
 * serving PDFs on the fly in a web application, shipping iText with a closed
 * source product.
 *
 * For more information, please contact iText Software Corp. at this
 * address: sales@itextpdf.com
 */
package com.itextpdf.tool.xml.parser.io;

import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.tool.xml.parser.XMLParserListener;

/**
 * Debugging util.
 * @author Balder Van Camp
 *
 */
public class ParserListenerWriter implements XMLParserListener {
	/**
	 *
	 */
	private final Appender writer;
	private final boolean formatted;

	/**
	 * @param writer
	 * @param formatted
	 */
	public ParserListenerWriter(final Appender writer, final boolean formatted) {
		this.writer = writer;
		this.formatted = formatted;
	}
	/**
	 * @param writer
	 */
	public ParserListenerWriter(final Appender writer) {
		this(writer, true);
	}

	public void unknownText(final String string) {
	}

	public void text(final String content) {
		writer.append(content);
	}

	public void startElement(final String currentTag, final Map<String, String> attributes) {
		if( attributes.size() >0) {
			writer.append("<"+currentTag+ " ");
			for (Entry<String,String> e : attributes.entrySet()) {
				writer.append(e.getKey()).append("=\"").append(e.getValue()).append("\"");
			}
			writer.append('>');
		} else {
			writer.append('<').append(currentTag).append('>');
		}
	}

	public void endElement(final String curentTag) {
		writer.append("</").append(curentTag).append('>');
		if (formatted) {
			writer.append((char) Character.LINE_SEPARATOR);
		}
	}

	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.ParserListener#comment(java.lang.String)
	 */
	public void comment(final String comment) {
		writer.append("<!--").append(comment).append("-->");
	}
	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.ParserListener#startDocument()
	 */
	public void startDocument() {
	}
	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.ParserListener#endDocument()
	 */
	public void endDocument() {
	}
}