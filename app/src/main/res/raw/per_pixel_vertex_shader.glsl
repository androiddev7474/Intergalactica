#version 300 es

uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;
uniform vec2 xyOffset; // offset koord för x, y
uniform vec2 whFrac; // texturend storlek i fraktion mot colormap (w resp h)
uniform int textID;
uniform float yCoord; //används för uvscroll i y-led

in vec4 a_Position;
in vec4 a_Color;
in vec3 a_Normal;
in vec2 a_TexCoordinate;

out vec2 v_TexCoordinate;

vec2 text_coord;
vec2 offset_vec;

void main()
{

    offset_vec = vec2(0, yCoord);

    v_TexCoordinate = a_TexCoordinate + offset_vec;

	gl_Position = u_MVPMatrix * a_Position;
}