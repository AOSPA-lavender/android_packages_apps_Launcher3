/*
 * Copyright (C) 2022 The Android Open Source Project
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
package com.android.launcher3.taskbar.allapps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.android.launcher3.R;
import com.android.launcher3.allapps.ActivityAllAppsContainerView;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.taskbar.overlay.TaskbarOverlayContext;

/** All apps container accessible from taskbar. */
public class TaskbarAllAppsContainerView extends
        ActivityAllAppsContainerView<TaskbarOverlayContext> {

    public TaskbarAllAppsContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaskbarAllAppsContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View inflateSearchBox() {
        if (isSearchSupported()) {
            return super.inflateSearchBox();
        }

        // Remove top padding of header, since we do not have any search
        mHeader.setPadding(mHeader.getPaddingLeft(), 0,
                mHeader.getPaddingRight(), mHeader.getPaddingBottom());

        TaskbarAllAppsFallbackSearchContainer searchView =
                new TaskbarAllAppsFallbackSearchContainer(getContext(), null);
        searchView.setId(R.id.search_container_all_apps);
        searchView.setVisibility(GONE);
        return searchView;
    }

    @Override
    protected boolean isSearchSupported() {
        return FeatureFlags.ENABLE_ALL_APPS_SEARCH_IN_TASKBAR.get();
    }

    @Override
    public boolean isInAllApps() {
        // All apps is always open
        return true;
    }
}
