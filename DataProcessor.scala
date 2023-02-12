package cse250.pa1
/**
 * cse250.pa1.DataProcessor
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

import cse250.objects.SolarInstallation
import scala.collection.mutable.ArrayBuffer

object DataProcessor {
  def splitArrayToRowArray(splitHeaderRow: Array[String]): Array[String] = {
    var v = 0 /* Accumulator*/
    var arr = new ArrayBuffer[String]()
    while (v < splitHeaderRow.length) { /*Accumulates to length of Array*/
      if (splitHeaderRow.length == 38) {
        if (splitHeaderRow(v).contains("\"")
          && !splitHeaderRow(v).contains(",")) {
          var acc = "\""
          for (i <- splitHeaderRow(v)) {
            if (i.toString == "\"") { acc += i + "\"" } else { acc += i }
          }
          arr = arr :+ acc
          v = v + 1
        } else if (splitHeaderRow(v).contains(",")) {
          val acc = "\"" + splitHeaderRow(v) + "\""
          arr = arr :+ acc
          v = v + 1
        } else {
          arr = arr :+ splitHeaderRow(v)
          v = v + 1
        }
      } else if (splitHeaderRow.length > 38) {
        if (v < splitHeaderRow.length && splitHeaderRow(v).contains("\"")) {
          var acc = splitHeaderRow(v).replace("\"", "") + ","
          v = v + 1
          while (v < splitHeaderRow.length && !splitHeaderRow(v).contains("\"")) {
            acc = acc + splitHeaderRow(v)
            v = v + 1
          }
          if (v < splitHeaderRow.length) {
            acc = acc + splitHeaderRow(v).replace("\"", "")
            arr = arr :+ acc
            v = v + 1
          }
        } else if (v < splitHeaderRow.length) {
          arr = arr :+ splitHeaderRow(v)
          v = v + 1
        }
      } else {
        while (v < splitHeaderRow.length) {
          arr = arr :+ splitHeaderRow(v)
          v = v + 1
        }
        while (arr.length != 38) {
          arr = arr :+ ""
        }
      }
    }
    arr.toArray
  }

  def rowArrayToSolarInstallation(rowData: Array[String]): SolarInstallation = {
    val solarInstallation = new SolarInstallation()
    val list = List(0,1,3,4,9,11,12,14,15,17,18,19,20,21,22,23,24,25,26,27,28,36,37,
      38,39,40,41,42,43) /* These are the required headers indicies */
    var s = 0 /* Accumulator */ 
    for (i <- list) { /* Loop through indicies */ 
      solarInstallation.fields += (SolarInstallation.REQUIRED_HEADERS(s) -> rowData(i)) /* Inserts the required header by string mapped to the value */
      s = s + 1 
    }
    solarInstallation
  }

  def computeUniqueCities(dataset: Array[SolarInstallation]): Int = {
    var totalCities:List[String] = List() /* Create List */
    for (i <- dataset){ /* Loop Array */
      val v = i.fields.getOrElse("Primary Inverter Manufacturer","") /* recieve value of the key */
      if (v.nonEmpty && v != "Primary Inverter Manufacturer"){ /* check if key exists */
        totalCities = totalCities :+ v 
      }
    }
    totalCities.distinct.length /* removes repeated values and returns the length */ 
  }


  def computeAverageCostForCity(dataset: Array[SolarInstallation], city: String): Double = {
    var Costs = 0.0 /* project costs accumulator */
    var numberOfProjects = 0.0 /* number of projects in the city */
    for (i <- dataset){
      if (i.toCSV(4).toString == city) { /* check if the project is in the given city */
        numberOfProjects += 1.0 
        val costOfInstallation: Int = i.toCSV(27).toInt /* make reference for project costs for effciency*/
        if (costOfInstallation > 0) { /* checks if cost is valid */
          Costs += costOfInstallation /* add costs to accumulator */
        }
      }
    }
    Costs/numberOfProjects
  }
}
