<?xml version="1.0" encoding="UTF-8"?>
<!-- Stylesheet to turn the XML output of CPD into a nice-looking HTML page -->
<!-- $Id: cpdhtml.xslt,v 1.1 2006/08/14 14:09:56 tomcopeland Exp $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/html4/loose.dtd" indent="yes"/>

<xsl:template match="com.tacitknowledge.pluginsupport.report.AggregateReport">
<html>
	<head>
		<style>
			.SummaryTitle  { }
			.SummaryNumber { background-color:#DDDDDD; text-align: center; }
            .passed { background-color:#00FF00; text-align: center; }
            .failed { background-color:#FF0000; text-align: center; }
            .ItemNumber    { background-color: #DDDDDD; }
			.CodeFragment  { background-color: #BBBBBB; display:none; font:normal normal normal 9pt Courier; }
			.ExpandButton  { background-color: #FFFFFF; font-size: 8pt; width: 20px; height: 20px; margin:0px; }
		</style>
	</head>
<body>
    <h2>Build Report Summary</h2>
    This page provides a dashboard of report results.
    <p/>

    <p><h4>Correctness Reports</h4></p>
    <table border="1" class="summary" cellpadding="2">
      <tr style="background-color:#CCCCCC;">
        <th>Module</th>
        <th>Report</th>
        <th>Description</th>
        <th>Status</th>
      </tr>
    <xsl:for-each select="//com.tacitknowledge.pluginsupport.report.ReportSummary[type='correctness']">
      <tr>
        <td class="SummaryNumber"><xsl:value-of select="moduleName"/></td>
        <td class="SummaryNumber"><a><xsl:attribute name="href"><xsl:value-of select="reportFile"/></xsl:attribute><xsl:value-of select="reportAlias"/></a></td>
        <td class="SummaryNumber"><xsl:value-of select="description"/></td>
        <xsl:choose>
            <xsl:when test="passed = 'true'">
              <td class="passed">Passed</td>
            </xsl:when>
            <xsl:otherwise>
               <td class="failed">Failed</td>
            </xsl:otherwise>
        </xsl:choose>

      </tr>
    </xsl:for-each>
    </table>
    <p/>
    <p><h4>Quality Metrics Reports</h4></p>
    <table border="1" class="summary" cellpadding="2">
      <tr style="background-color:#CCCCCC;">
        <th>Module</th>
        <th>Report</th>
        <th>Description</th>
        <th>Status</th>
      </tr>
    <xsl:for-each select="//com.tacitknowledge.pluginsupport.report.ReportSummary[type='quality']">
      <tr>
        <td class="SummaryNumber"><xsl:value-of select="moduleName"/></td>
        <td class="SummaryNumber"><a><xsl:attribute name="href"><xsl:value-of select="reportFile"/></xsl:attribute><xsl:value-of select="reportAlias"/></a></td>
        <td class="SummaryNumber"><xsl:value-of select="description"/></td>
        <xsl:choose>
            <xsl:when test="passed = 'true'">
              <td class="passed">Passed</td>
            </xsl:when>
            <xsl:otherwise>
               <td class="failed">Failed</td>
            </xsl:otherwise>
        </xsl:choose>

      </tr>
    </xsl:for-each>
    </table>
    <p/>
    <p><h4>Informational Reports</h4></p>
    <table border="1" class="summary" cellpadding="2">
      <tr style="background-color:#CCCCCC;">
        <th>Module</th>
        <th>Report</th>
        <th>Description</th>
        <th>Status</th>
      </tr>
    <xsl:for-each select="//com.tacitknowledge.pluginsupport.report.ReportSummary[type='info']">
      <tr>
        <td class="SummaryNumber"><xsl:value-of select="moduleName"/></td>
        <td class="SummaryNumber"><a><xsl:attribute name="href"><xsl:value-of select="reportFile"/></xsl:attribute><xsl:value-of select="reportAlias"/></a></td>
        <td class="SummaryNumber"><xsl:value-of select="description"/></td>
        <xsl:choose>
            <xsl:when test="passed = 'true'">
              <td class="passed">Passed</td>
            </xsl:when>
            <xsl:otherwise>
               <td class="failed">Failed</td>
            </xsl:otherwise>
        </xsl:choose>

      </tr>
    </xsl:for-each>
    </table>
    <p/>
    <p><h4>Reports Coming Soon</h4></p>
    <table border="1" class="summary" cellpadding="2">
      <tr style="background-color:#CCCCCC;">
        <th>Module</th>
        <th>Report</th>
        <th>Description</th>
        <th>Status</th>
      </tr>
    <xsl:for-each select="//com.tacitknowledge.pluginsupport.report.ReportSummary[type='tbd']">
      <tr>
        <td class="SummaryNumber"><xsl:value-of select="moduleName"/></td>
        <td class="SummaryNumber"><a><xsl:attribute name="href"><xsl:value-of select="reportFile"/></xsl:attribute><xsl:value-of select="reportAlias"/></a></td>
        <td class="SummaryNumber"><xsl:value-of select="description"/></td>
        <xsl:choose>
            <xsl:when test="passed = 'true'">
              <td class="passed">Passed</td>
            </xsl:when>
            <xsl:otherwise>
               <td class="failed">Failed</td>
            </xsl:otherwise>
        </xsl:choose>

      </tr>
    </xsl:for-each>
    </table>

</body>
</html>
</xsl:template>

</xsl:stylesheet>
