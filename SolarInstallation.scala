package cse250.objects
/**
 * cse250.objects.SolarInstallation.scala
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

import scala.collection.mutable

/**
 * One specific solar installation site.
 */
class SolarInstallation
{
  /**
   * Key-value pairs representing data about the solar site.  See [[SolarInstallation.HEADERS]] for a list of
   * allowable keys, and [[SolarInstallation.REQUIRED_HEADERS]] for a list of mandatory keys.
   */
  val fields: mutable.Map[String, String] = new mutable.HashMap[String, String]

  /**
   * Generate a human-readable representation of the installation
   * @return A human (but not machine)-readable representation of the [[SolarInstallation.REQUIRED_HEADERS]] of this site.
   */
  override def toString: String =
  {
    SolarInstallation.REQUIRED_HEADERS
      .map { h => fields.getOrElse(h, "**MISSING HEADER**") }
      .mkString(",")
  }
  /**
   * Generate a human-readable representation of the installation
   * @return A CSV encoding of the [[SolarInstallation.REQUIRED_HEADERS]] for this site.
   */
  def toCSV: String =
  {
    SolarInstallation.REQUIRED_HEADERS
      .map { h => fields.getOrElse(h, "**MISSING HEADER**") }
      .mkString("\"", "\",\"", "\"")
  }

  // Scala Cookbook: Equals
  // https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch04s16.html

  /**
   * Fast check for potential equivalence
   * @param a   An object instance to test for potential equivalence
   * @return    True if the object can potentially be equal, False if the object is definitely not equal
   */
  def canEqual(a: Any): Boolean = a.isInstanceOf[SolarInstallation]

  /**
   * Test for exact equality
   * @param that  An object to test for equivalence
   * @return    True if the object is definitely equal.
   */
  override def equals(that: Any): Boolean =
    that match {
      case that: SolarInstallation => that.canEqual(this) && this.hashCode.equals(that.hashCode) && this.fields.equals(that.fields)
      case _ => false
    }

  /**
   * Generate a hash code for this site (used by HashMap)
   * @return    A deterministic, pseudorandom integer based on this site's data.
   */
  override def hashCode: Int = fields.hashCode
}

object SolarInstallation
{
  /**
   * Headers of the base SolarInstallation dataset.
   */
  val HEADERS = Seq(
    "Reporting Period",
    "Project Number",
    "Legacy Project Number",
    "Street Address",
    "City",
    "County",
    "State",
    "ZIP Code",
    "Incorporated Municipality",
    "Municipality Type",
    "Census Tract",
    "Sector",
    "Program Type",
    "Solicitation",
    "Electric Utility",
    "Purchase Type",
    "Date Application Received",
    "Date Completed",
    "Project Status",
    "Contractor",
    "Minority or Women Owned Business Enterprise (MWBE)",
    "Primary Inverter Manufacturer",
    "Primary Inverter Model Number",
    "Total Inverter Quantity",
    "Primary PV Module Manufacturer",
    "PV Module Model Number",
    "Total PV Module Quantity",
    "Project Cost",
    "Total NYSERDA Incentive",
    "Affordable Solar Residential Adder",
    "Affordable Multifamily Housing Incentive",
    "Community Adder",
    "Inclusive Community Solar Adder",
    "Expanded Solar For All Adder",
    "Brownfield/Landfill Adder",
    "Canopy Adder",
    "Total Nameplate kW DC",
    "Expected KWh Annual Production",
    "Remote Net Metering",
    "Community Distributed Generation",
    "Green Jobs Green New York Participant",
    "Latitude",
    "Longitude",
    "Georeference",
  )

  /**
   * Headers that must be present in a [[SolarInstallation]] instance.
   */
  val REQUIRED_HEADERS = Seq(
    "Reporting Period",
    "Project Number",
    "Street Address",
    "City",
    "Municipality Type",
    "Sector",
    "Program Type",
    "Electric Utility",
    "Purchase Type",
    "Date Completed",
    "Project Status",
    "Contractor",
    "Minority or Women Owned Business Enterprise (MWBE)",
    "Primary Inverter Manufacturer",
    "Primary Inverter Model Number",
    "Total Inverter Quantity",
    "Primary PV Module Manufacturer",
    "PV Module Model Number",
    "Total PV Module Quantity",
    "Project Cost",
    "Total NYSERDA Incentive",
    "Total Nameplate kW DC",
    "Expected KWh Annual Production",
    "Remote Net Metering",
    "Community Distributed Generation",
    "Green Jobs Green New York Participant",
    "Latitude",
    "Longitude",
    "Georeference",
  )

}

