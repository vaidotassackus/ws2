<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/student">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="page" page-width="210mm" page-height="297mm"
                                       margin="20mm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="page">
                <fo:flow flow-name="xsl-region-body" font-family="Helvetica" font-size="11pt">
                    <fo:block font-size="18pt" font-weight="bold" space-after="10mm">
                        <xsl:value-of select="name"/>
                    </fo:block>
                    <fo:block>ID: <xsl:value-of select="id"/></fo:block>
                    <fo:block>Age: <xsl:value-of select="age"/></fo:block>
                    <fo:block>Average grade: <xsl:value-of select="averageGrade"/></fo:block>
                    <fo:block>Active: <xsl:value-of select="active"/></fo:block>
                    <fo:block>Initial: <xsl:value-of select="initial"/></fo:block>
                    <fo:block font-size="14pt" font-weight="bold" space-before="10mm" space-after="4mm">
                        Subjects
                    </fo:block>
                    <fo:table table-layout="fixed" width="100%" border="0.5pt solid black">
                        <fo:table-column column-width="70%"/>
                        <fo:table-column column-width="30%"/>
                        <fo:table-body>
                            <fo:table-row background-color="#eeeeee">
                                <fo:table-cell padding="5pt"><fo:block>Name</fo:block></fo:table-cell>
                                <fo:table-cell padding="5pt"><fo:block>Credits</fo:block></fo:table-cell>
                            </fo:table-row>
                            <xsl:for-each select="subjects/subject">
                                <fo:table-row>
                                    <fo:table-cell padding="5pt"><fo:block><xsl:value-of select="name"/></fo:block></fo:table-cell>
                                    <fo:table-cell padding="5pt"><fo:block><xsl:value-of select="credits"/></fo:block></fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
