/*
 * semanticcms-news-model - SemanticCMS newsfeeds.
 * Copyright (C) 2016, 2017, 2020, 2021, 2022, 2023  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-news-model.
 *
 * semanticcms-news-model is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-news-model is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-news-model.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.semanticcms.news.model;

import static com.aoapps.lang.Strings.nullIfEmpty;

import com.semanticcms.core.model.Element;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

/**
 * Models one news entry.
 */
public class News extends Element implements Comparable<News> {

  // Target of news entry, will be the parent page/element of the news entry when not specified
  private volatile String book;
  private volatile String targetPage;
  private volatile String element;
  private volatile String view;
  // The title of the news entry, will be the target page title/element label when not specified
  private volatile String title;
  // The optional short description of the news entry, this is text-only - no HTML, all HTML will be escaped.  For HTML use body.
  private volatile String description;
  // TODO: comments once a comment system is enabled
  // guid: generated from target
  // Java 1.8: No longer use joda time
  private volatile DateTime pubDate; // Required, maybe a future version could interact with versioning systems
  private volatile Boolean allowRobots;

  /**
   * Ordered by pubDate desc, page.
   */
  @Override
  public int compareTo(News o) {
    int diff = o.getPubDate().compareTo(getPubDate());
    if (diff != 0) {
      return diff;
    }
    return getPage().compareTo(o.getPage());
  }

  @Override
  public String getLabel() {
    return getTitle();
  }

  /**
   * News elements are not part of the content directly, so are hidden from tree views.
   */
  @Override
  public boolean isHidden() {
    return true;
  }

  public String getBook() {
    return book;
  }

  public void setBook(String book) {
    checkNotFrozen();
    this.book = nullIfEmpty(book);
  }

  public String getTargetPage() {
    return targetPage;
  }

  public void setTargetPage(String targetPage) {
    checkNotFrozen();
    this.targetPage = nullIfEmpty(targetPage);
  }

  public String getElement() {
    return element;
  }

  public void setElement(String element) {
    checkNotFrozen();
    this.element = nullIfEmpty(element);
  }

  public String getView() {
    return view;
  }

  public void setView(String view) {
    checkNotFrozen();
    this.view = nullIfEmpty(view);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    checkNotFrozen();
    this.title = nullIfEmpty(title);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    checkNotFrozen();
    this.description = nullIfEmpty(description);
  }

  public DateTime getPubDate() {
    return pubDate;
  }

  public void setPubDate(ReadableDateTime pubDate) {
    checkNotFrozen();
    this.pubDate = pubDate == null ? null : pubDate.toDateTime();
  }

  /**
   * Gets the allowRobots setting.
   * <ul>
   *   <li>{@literal null} (The default) - Inherit setting from page</li>
   *   <li>{@literal true} - Robots allowed</li>
   *   <li>{@literal false} - Robots not allowed</li>
   * </ul>
   */
  public Boolean getAllowRobots() {
    return allowRobots;
  }

  public void setAllowRobots(Boolean allowRobots) {
    checkNotFrozen();
    this.allowRobots = allowRobots;
  }

  @Override
  protected String getDefaultIdPrefix() {
    return "news";
  }
}
