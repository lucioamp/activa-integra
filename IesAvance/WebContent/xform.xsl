<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="html" encoding="ISO-8859-1" />

   <xsl:template match="/">
      <html>
         <title>Title</title>

         <body>
            <form>
               <xsl:attribute name="action">
                  <xsl:value-of select="xforms/model/submission/@action" />
               </xsl:attribute>

               <xsl:attribute name="method">POST</xsl:attribute>

               <xsl:attribute name="name">
                  <xsl:value-of select="xforms/model/submission/@id" />
               </xsl:attribute>

               <xsl:attribute name="onsubmit">return abreFormulario(this, true)</xsl:attribute>

               <table border="1">
                  <xsl:apply-templates select="/xforms/*[name()='input' or name()='textarea']" />

                  <tr>
                     <td colspan='2'>
                        <input>
                           <xsl:attribute name="type">submit</xsl:attribute>

                           <xsl:attribute name="value">
                              <xsl:value-of select="//submit/label" />
                           </xsl:attribute>
                        </input>
                     </td>
                  </tr>
               </table>
            </form>
         </body>
      </html>
   </xsl:template>

   <xsl:template match="input">
      <tr>
         <td>
            <xsl:value-of select="label" />
         </td>

         <td>
            <input>
               <xsl:attribute name="type">text</xsl:attribute>

               <xsl:attribute name="name">
                  <xsl:value-of select="@ref" />
               </xsl:attribute>
            </input>
         </td>
      </tr>
   </xsl:template>

   <xsl:template match="textarea">
      <tr>
         <td>
            <xsl:value-of select="label" />
         </td>

         <td>
            <textarea>
               <xsl:attribute name="name">
                  <xsl:value-of select="@ref" />
               </xsl:attribute>
            </textarea>
         </td>
      </tr>
   </xsl:template>
</xsl:stylesheet>

