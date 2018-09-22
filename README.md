# ViewPagerIndicatorKt

A simple view pager indicator implementation in Kotlin.

### Usage

Use in you layout, you can set indicator drawable resources directly in the xml.

```
<com.emreeran.viewpagerindicatorkt.ViewPagerIndicatorKt
    android:id="@+id/indicator"
    android:layout_width="match_parent"
    android:layout_height="28dp"
    app:default_drawable="@drawable/circle_gray"
    app:item_height="8dp"
    app:item_margin="16dp"
    app:item_width="8dp"
    app:selected_drawable="@drawable/circle_black" />
```

Set your adapter first and than set the pager to indicator

```
val adapter = PagerAdapter(supportFragmentManager)
pager.adapter = adapter
indicator.viewPager = pager
```

Elements can also start from the right and move right-to-left if you set the `isRtl` flag to true or add `app:rtl="true"` to the xml.

### License

```
Copyright 2018 Emre Eran

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
```