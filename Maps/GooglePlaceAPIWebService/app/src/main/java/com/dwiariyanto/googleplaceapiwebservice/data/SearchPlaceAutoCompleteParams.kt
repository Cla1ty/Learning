/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Tuesday, March 27, 2018 at 23:30                                                               *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.googleplaceapiwebservice.data

class SearchPlaceAutoCompleteParams(
        val long: Float,
        val lang: Float,
        val radius: Int = 25000,
        val type: String = "address"
)
