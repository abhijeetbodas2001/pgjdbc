/*
 * Copyright (c) 2004, PostgreSQL Global Development Group
 * See the LICENSE file in the project root for more information.
 */
// Copyright (c) 2004, Open Cloud Limited.

package org.postgresql.jdbc;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

/* CS631 start */
import com.google.gson.Gson;
/* CS631 end */

import java.sql.ResultSet;

/**
 * Helper class that storing result info. This handles both the ResultSet and no-ResultSet result
 * cases with a single interface for inspecting and stepping through them.
 *
 * @author Oliver Jowett (oliver@opencloud.com)
 */
public class ResultWrapper {
  /* CS631 start */
  public static ResultWrapper copy(Object orig) {
    // Implements Deep copy of the ResultWrapper instance using
    // serialization and deserialization in JSON.
    // This is done using the GSON library.
    Gson gson = new Gson();
    return gson.fromJson(gson.toJson(orig), ResultWrapper.class);
  }
  /* CS631 end */

  public ResultWrapper(@Nullable ResultSet rs) {
    this.rs = rs;
    this.updateCount = -1;
    this.insertOID = -1;
  }

  public ResultWrapper(long updateCount, long insertOID) {
    this.rs = null;
    this.updateCount = updateCount;
    this.insertOID = insertOID;
  }

  @Pure
  public @Nullable ResultSet getResultSet() {
    return rs;
  }

  public long getUpdateCount() {
    return updateCount;
  }

  public long getInsertOID() {
    return insertOID;
  }

  public @Nullable ResultWrapper getNext() {
    return next;
  }

  public void append(ResultWrapper newResult) {
    ResultWrapper tail = this;
    while (tail.next != null) {
      tail = tail.next;
    }

    tail.next = newResult;
  }

  private final @Nullable ResultSet rs;
  private final long updateCount;
  private final long insertOID;
  private @Nullable ResultWrapper next;
}
