package com.example.sampleui.utils

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 11:11 PM
 */
class DateUtils {

   companion object{
       fun getTimestampString(): String{
           return System.currentTimeMillis().toString()
       }

       fun getTimestamp(): Long{
           return System.currentTimeMillis()
       }
   }
}