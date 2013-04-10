/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-04-09 19:13:20 UTC)
 * on 2013-04-10 at 05:44:17 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.fmthings.model;

/**
 * Model definition for DBItem.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-api-java-client/wiki/Json">http://code.google.com/p/google-api-java-client/wiki/Json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class DBItem extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String category;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime date;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String location;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer reward;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> searchTokens;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String searchableContent;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime submittedDate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String submittingUser;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String type;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCategory() {
    return category;
  }

  /**
   * @param category category or {@code null} for none
   */
  public DBItem setCategory(java.lang.String category) {
    this.category = category;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getDate() {
    return date;
  }

  /**
   * @param date date or {@code null} for none
   */
  public DBItem setDate(com.google.api.client.util.DateTime date) {
    this.date = date;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description description or {@code null} for none
   */
  public DBItem setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLocation() {
    return location;
  }

  /**
   * @param location location or {@code null} for none
   */
  public DBItem setLocation(java.lang.String location) {
    this.location = location;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name name or {@code null} for none
   */
  public DBItem setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getReward() {
    return reward;
  }

  /**
   * @param reward reward or {@code null} for none
   */
  public DBItem setReward(java.lang.Integer reward) {
    this.reward = reward;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getSearchTokens() {
    return searchTokens;
  }

  /**
   * @param searchTokens searchTokens or {@code null} for none
   */
  public DBItem setSearchTokens(java.util.List<java.lang.String> searchTokens) {
    this.searchTokens = searchTokens;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSearchableContent() {
    return searchableContent;
  }

  /**
   * @param searchableContent searchableContent or {@code null} for none
   */
  public DBItem setSearchableContent(java.lang.String searchableContent) {
    this.searchableContent = searchableContent;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getSubmittedDate() {
    return submittedDate;
  }

  /**
   * @param submittedDate submittedDate or {@code null} for none
   */
  public DBItem setSubmittedDate(com.google.api.client.util.DateTime submittedDate) {
    this.submittedDate = submittedDate;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSubmittingUser() {
    return submittingUser;
  }

  /**
   * @param submittingUser submittingUser or {@code null} for none
   */
  public DBItem setSubmittingUser(java.lang.String submittingUser) {
    this.submittingUser = submittingUser;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getType() {
    return type;
  }

  /**
   * @param type type or {@code null} for none
   */
  public DBItem setType(java.lang.String type) {
    this.type = type;
    return this;
  }

  @Override
  public DBItem set(String fieldName, Object value) {
    return (DBItem) super.set(fieldName, value);
  }

  @Override
  public DBItem clone() {
    return (DBItem) super.clone();
  }

}
