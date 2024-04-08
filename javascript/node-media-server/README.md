# Live streaming using node-media-server

## Usage
1. Start the node-media-server
    - node-media-server is able to receive streams using RTMP and WebSocket natively
    - it also exposes a web admin interface (in this example at location http://localhost:8001/admin)

2. Stream a video
    - 1. Using OBS
        - RTMP stream
            - click on Settings/Streaming and set the properties as:
                - Service: Custom
                - Server: rtmp://localhost:1934/live
                - Stream key: stream

        - WebSocket stream
            - a modified version of OBS that supports streaming through WebRTC is available [here](https://github.com/CoSMoSoftware/OBS-studio-webrtc)
            - you might need to use Dolby Milicast to make it work without too much hassle - more info [here](https://dolby.io/blog/using-webrtc-in-obs-for-remote-live-production/)

    - 2. Using ffmpeg
        - You can run the following command to start streaming using ffmpeg:
            ```bash
            ffmpeg -f avfoundation -framerate 30 -video_size 1280x720 -i "0:0" -c:v libx264 -pix_fmt yuv420p -preset veryfast -g 60 -keyint_min 60 -b:v 2500k -maxrate 2500k -bufsize 5000k -f flv rtmp://localhost:1934/live/stream
            ```

3. Configure node-media-server to publish the stream through on channels
    - check the [node-media-server](https://www.npmjs.com/package/node-media-server) documentation and examples for more info
    - you can also configure fission to enable real-time transcoding

3. Consume the published stream in your app
    - [as Dash](./client/index-dash.html)
    - [as FLV](./client/index-flv.html)
    - [as hls natively (only in Safari)](./client/index-hls-safari.html)
    - [hls-js](./client/index-hls-js.html)

4. Quit your job and become a Twitch streamer
