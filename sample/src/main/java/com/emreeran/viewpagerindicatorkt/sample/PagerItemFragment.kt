/*
 * Copyright (C) 2018 Emre Eran
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

package com.emreeran.viewpagerindicatorkt.sample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emreeran.viewpagerindicatorkt.sample.databinding.FragmentPagerItemBinding

/**
 * Created by Emre Eran on 22.09.2018.
 */
class PagerItemFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPagerItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_pager_item, container, false)
        binding.position = arguments?.getInt(ARGS_POS)
        return binding.root
    }

    companion object {
        private const val ARGS_POS = "pos"

        fun create(pos: Int): PagerItemFragment {
            val args = Bundle()
            args.putInt(ARGS_POS, pos)
            val fragment = PagerItemFragment()
            fragment.arguments = args
            return fragment
        }
    }
}