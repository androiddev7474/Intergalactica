#version 300 es

uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;
uniform vec2 xyOffset; // offset koord för x, y
uniform vec2 whFrac; // texturend storlek i fraktion mot colormap (w resp h)
uniform int textID;

in vec4 a_Position;
in vec4 a_Color;
in vec3 a_Normal;
in vec2 a_TexCoordinate;

out vec2 v_TexCoordinate;

vec2 text_coord;
vec2 offset_vec;

void main()
{

    text_coord = a_TexCoordinate * whFrac;
    text_coord = text_coord + xyOffset;
    v_TexCoordinate = text_coord;


    /*text_coord = a_TexCoordinate * 0.5;
    text_coord = text_coord + 0.05;
    v_TexCoordinate = text_coord;
    */

    //v_TexCoordinate = a_TexCoordinate;


	gl_Position = u_MVPMatrix * a_Position;
}
