precision highp float;
varying vec2 textureCoordinate;
uniform sampler2D inputImageTexture;
uniform float uIntensity; // -1 ~ 1
void main()
{
    vec2 uv = textureCoordinate;
    vec2 center = vec2(0.5, 0.5);
    vec2 vec = uv - center;

    float dis = length(vec);

    float w = 0.5;
    float h = 0.5;
    float x = uv.x - 0.5;
    float y = uv.y - 0.5;

    if (abs(x) >= abs(y)) {
        h = y / x * w;
    } else {
        w = x / y * h;
    }

    float intensity = abs(uIntensity);
    bool convex = uIntensity > 0.;

    float edgeLen = sqrt(pow(x, 2.) + pow(y, 2.) + 0.25);
    float recommLen = 0.707 + (edgeLen - 0.707) * (convex ? 0.3 : 0.5);
    float ratio = convex ? recommLen / edgeLen : edgeLen / recommLen;

    vec2 p = center + mix(vec, vec / ratio, intensity);

    gl_FragColor = texture2D(inputImageTexture, p);
}