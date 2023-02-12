/**
 * AssessmentDataProcessorTests.scala
 *
 * Copyright 2022 Oliver Kennedy (okennedy@buffalo.edu)
 *           2022 Eric Mikida (epmikida@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 */
package cse250.pa1

import cse250.objects.SolarInstallation
import org.scalatest.flatspec.AnyFlatSpec


class DataProcessorTests extends AnyFlatSpec {
  behavior of "DataProcessor.splitArrayToRowArray"
  it should "return an Array with 44 entries when processing the header row" in {
    val headerRow = SolarInstallation.HEADERS.mkString(",")
    val splitHeaderRow = headerRow.split(',')
    val result = DataProcessor.splitArrayToRowArray(splitHeaderRow)
    assert(result.length == SolarInstallation.HEADERS.size)
    for (i <- splitHeaderRow.indices) assert(splitHeaderRow(i) == result(i))
  }


  it should "produces an array of correct length when processing the first entry (2nd row) of dataset" in {
    val splitSecondRow = SECOND_ROW.split(',')
    val result = DataProcessor.splitArrayToRowArray(splitSecondRow)
    assert(result.length == SolarInstallation.HEADERS.size)
    for (i <- EXPECTED_SECOND_ROW.indices) assert(result(i) == EXPECTED_SECOND_ROW(i))
  }

  behavior of "AssessmentDataProcessor.rowArrayToSolarInstallation"
  it should "return an exactly the required header fields" in {
    val headerRow = SolarInstallation.HEADERS.mkString(",")
    val splitHeaderRow = headerRow.split(',')
    val rowArray = DataProcessor.splitArrayToRowArray(splitHeaderRow)
    val result = DataProcessor.rowArrayToSolarInstallation(rowArray)
    assert(result.fields.size == SolarInstallation.REQUIRED_HEADERS.length)
    assert(result.toString == SolarInstallation.REQUIRED_HEADERS.mkString("",",",""))
  }

  it should "correctly process the first entry (2nd row) of file" in {
    val splitSecondRow = SECOND_ROW.split(',')
    val rowArray = DataProcessor.splitArrayToRowArray(splitSecondRow)
    assert(rowArray.length == SolarInstallation.HEADERS.size)
    val result = DataProcessor.rowArrayToSolarInstallation(rowArray)
    assert(result.fields.size == SolarInstallation.REQUIRED_HEADERS.length)
    val expectedToString = EXPECTED_SECOND_ROW_REQUIRED.mkString(",")
    assert(result.toString == expectedToString)
  }

  val SECOND_ROW = "01/31/2023,0000534735,,,Albany,Albany,NY,12205,Colonie,Town,36001014001,Residential,Residential/Small Commercial,PON 2112,National Grid,Purchase,12/17/2022,,Pipeline,,No,SunPower,SPR-M425-H-AC [240V],12,Sunpower,SPR-M425-H-AC,12,26555.00,2040.00,0,0,0,0,0,0,0,5.10,5710.00,,No,No,42.7056536,-73.8100987,POINT (-73.8100987 42.7056536)"
  val EXPECTED_SECOND_ROW = Array(
    "01/31/2023",
    "0000534735",
    "",
    "",
    "Albany",
    "Albany",
    "NY",
    "12205",
    "Colonie",
    "Town",
    "36001014001",
    "Residential",
    "Residential/Small Commercial",
    "PON 2112",
    "National Grid",
    "Purchase",
    "12/17/2022",
    "",
    "Pipeline",
    "",
    "No",
    "SunPower",
    "SPR-M425-H-AC [240V]",
    "12",
    "Sunpower",
    "SPR-M425-H-AC",
    "12",
    "26555.00",
    "2040.00",
    "0",
    "0",
    "0",
    "0",
    "0",
    "0",
    "0",
    "5.10",
    "5710.00",
    "",
    "No",
    "No",
    "42.7056536",
    "-73.8100987",
    "POINT (-73.8100987 42.7056536)",
  )
  val EXPECTED_SECOND_ROW_REQUIRED =
    Seq(0, 1, 3, 4, 9, 11, 12, 14, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 36, 37, 38, 39, 40, 41, 42, 43)
      .map { EXPECTED_SECOND_ROW(_) }
      .toArray

}
