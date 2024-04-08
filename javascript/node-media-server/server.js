const NodeMediaServer = require('node-media-server');

const config = {
  rtmp: {
    port: 1934,
    chunk_size: 60000,
    gop_cache: true,
    ping: 60,
    ping_timeout: 30
  },
  http: {
    port: 8000,
    mediaroot: '/Users/axbg/Desktop/stream/mediaroot',
    allow_origin: '*'
  },
  trans: {
    ffmpeg: '/opt/homebrew/bin/ffmpeg',
    tasks: [
      {
        app: 'live',
        hls: true,
        hlsFlags: '[hls_time=2:hls_list_size=3:hls_flags=delete_segments]',
        // hlsKeep: true, // to prevent .hls file delete after end the stream
        dash: true,
        dashFlags: '[f=dash:window_size=3:extra_window_size=5]',
        // dashKeep: true // to prevent .dash file delete after end the stream
      }
    ]
  }
};

var nms = new NodeMediaServer(config)
nms.run();

