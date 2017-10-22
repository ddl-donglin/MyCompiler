public class HTMLDFA {
    public static String DFAStr = "<html>\n" +
            "    <body>\n" +
            "    \n" +
            "    <table border=\"1\">\n" +
            "      <tr>\n" +
            "        <th>     </th>\n" +
            "        <th>letter_</th>\n" +
            "        <th>letter,digit</th>\n" +
            "        <th>digit</th>\n" +
            "        <th>1-9</th>\n" +
            "        <th>0-9</th>\n" +
            "        <th>0</th>\n" +
            "        <th>0-7</th>\n" +
            "        <th>x</th>\n" +
            "        <th>0-9,a-f.A-F</th>\n" +
            "        <th>:</th>\n" +
            "        <th>=</th>\n" +
            "        <th>+</th>\n" +
            "        <th>/</th>\n" +
            "        <th>*</th>\n" +
            "        <th>others</th>\n" +
            "        <th>separators</th>\n" +
            "        <th>operator</th>\n" +
            "        <th>{</th>\n" +
            "        <th>others</th>\n" +
            "        <th>}</th>\n" +
            "        <th>[</th>\n" +
            "        <th>]</th>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 0</td>\n" +
            "        <td> 1</td>\n" +
            "        <td> </td>\n" +
            "        <td> 2</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 10</td>\n" +
            "        <td> </td>\n" +
            "        <td> 12</td>\n" +
            "        <td> 14</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 18</td>\n" +
            "        <td> 19</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 1</td>\n" +
            "        <td> </td>\n" +
            "        <td> 1</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 2</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 3</td>\n" +
            "        <td> </td>\n" +
            "        <td> 4/5/7</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 3</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 3</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 4</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 6</td>\n" +
            "        <td> 8</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 5</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 6</td>\n" +
            "        <td> 8</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 6</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 6</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 7</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 6</td>\n" +
            "        <td> 8</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 8</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 9</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td> 9</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 9</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>10</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 11</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>11</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>12</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 13</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>13</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>14</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 15</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>15</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 16</td>\n" +
            "        <td> 15</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>16</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 17</td>\n" +
            "        <td> 16</td>\n" +
            "        <td> 15</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>17</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>18</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 22</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 20</td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>19</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 19</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>20</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 20</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 21</td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>21</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>22</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 22</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td>23</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "        <td> 23</td>\n" +
            "        <td> </td>\n" +
            "        <td> </td>\n" +
            "      </tr>\n" +
            "    </table>\n" +
            "    \n" +
            "    </body>\n" +
            "    </html>";
}
