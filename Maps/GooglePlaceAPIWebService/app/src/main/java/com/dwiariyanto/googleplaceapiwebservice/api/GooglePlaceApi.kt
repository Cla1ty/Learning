/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Tuesday, March 27, 2018 at 23:28                                                               *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.googleplaceapiwebservice.api

interface GooglePlaceApi{

    @GET("autocomplete/json?location=-6.2408501,106.8481757&radius=5000&type=address&input=casab&key=AIzaSyAKO7x_d-U7TjuOjBM9WcGwN7R2WtvVMi4")
    fun searchPlaceAutoComplete()
}
