/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.data;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Immutable model class for a Task.
 */
public final class Task {

  @NonNull
  private final String mId;

  @Nullable
  private final String mTitle;

  @Nullable
  private final String mDescription;

  private final boolean mCompleted;

  /**
   * Use this constructor to create a new active Task.
   *
   * @param title title of the task
   * @param description description of the task
   */
  public Task(@Nullable String title, @Nullable String description) {
    this(title, description, UUID.randomUUID().toString(), false);
  }

  /**
   * Use this constructor to create an active Task if the Task already has an id (copy of another
   * Task).
   *
   * @param title title of the task
   * @param description description of the task
   * @param id id of the task
   */
  public Task(@Nullable String title, @Nullable String description, @NonNull String id) {
    this(title, description, id, false);
  }

  /**
   * Use this constructor to create a new completed Task.
   *
   * @param title title of the task
   * @param description description of the task
   * @param completed true if the task is completed, false if it's active
   */
  public Task(@Nullable String title, @Nullable String description, boolean completed) {
    this(title, description, UUID.randomUUID().toString(), completed);
  }

  /**
   * Use this constructor to specify a completed Task if the Task already has an id (copy of another
   * Task).
   *
   * @param title title of the task
   * @param description description of the task
   * @param id id of the task
   * @param completed true if the task is completed, false if it's active
   */
  public Task(@Nullable String title, @Nullable String description,
      @NonNull String id, boolean completed) {
    mId = id;
    mTitle = title;
    mDescription = description;
    mCompleted = completed;
  }

  @NonNull
  public String getId() {
    return mId;
  }

  @Nullable
  public String getTitle() {
    return mTitle;
  }

  @Nullable
  public String getTitleForList() {
    if (!TextUtils.isEmpty(mTitle)) {
      return mTitle;
    } else {
      return mDescription;
    }
  }

  @Nullable
  public String getDescription() {
    return mDescription;
  }

  public boolean isCompleted() {
    return mCompleted;
  }

  public boolean isActive() {
    return !mCompleted;
  }

  public boolean isEmpty() {
    return TextUtils.isEmpty(mTitle) &&
        TextUtils.isEmpty(mDescription);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return TextUtils.equals(mId, task.mId) &&
        TextUtils.equals(mTitle, task.mTitle) &&
        TextUtils.equals(mDescription, task.mDescription);
  }

  @TargetApi(VERSION_CODES.KITKAT)
  @Override
  public int hashCode() {
    List<String> list = new ArrayList<>();
    list.add(mId);
    list.add(mDescription);
    list.add(mTitle);
    return Objects.hashCode(list);
  }

  @Override
  public String toString() {
    return "Task with title " + mTitle;
  }
}