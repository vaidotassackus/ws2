<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/student">
        <html lang="en">
            <head>
                <meta charset="UTF-8"/>
                <title>Student: <xsl:value-of select="name"/></title>
                <style>
                    body { font-family: sans-serif; max-width: 720px; margin: 40px auto; color: #222; }
                    table { border-collapse: collapse; width: 100%; }
                    th, td { border: 1px solid #aaa; padding: 8px; text-align: left; }
                    th { background: #eee; }
                </style>
            </head>
            <body>
                <h1><xsl:value-of select="name"/></h1>
                <p>ID: <xsl:value-of select="id"/></p>
                <p>Age: <xsl:value-of select="age"/></p>
                <p>Average grade: <xsl:value-of select="averageGrade"/></p>
                <p>Active: <xsl:value-of select="active"/></p>
                <p>Initial: <xsl:value-of select="initial"/></p>
                <h2>Subjects</h2>
                <table>
                    <tr><th>Name</th><th>Credits</th></tr>
                    <xsl:for-each select="subjects/subject">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="credits"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
