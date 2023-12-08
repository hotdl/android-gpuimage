precision highp float;
varying vec2 textureCoordinate;
uniform sampler2D inputImageTexture;
uniform float uGamma; // 0 ~ 3

void main() {
    vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);
    gl_FragColor = vec4(pow(textureColor.rgb, vec3(uGamma)), textureColor.w);
}