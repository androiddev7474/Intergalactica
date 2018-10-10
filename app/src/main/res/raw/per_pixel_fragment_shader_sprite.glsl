#version 300 es

precision mediump float;

uniform sampler2D u_Texture;
uniform float fraction;
uniform int isFadingPx; // flagga för om texturen håller på att göra en fade

in vec2 v_TexCoordinate;

vec4 PixelColor[2];

out vec4 fragmentColor;

void main()
{

    fragmentColor = texture(u_Texture, v_TexCoordinate);

}
