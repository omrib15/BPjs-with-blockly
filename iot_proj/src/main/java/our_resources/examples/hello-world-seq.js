bp.registerBThread("bt-world",function(){
  bsync({waitFor:bp.Event("hello")});
  bsync({request:bp.Event("world")});
})

bp.registerBThread("bt-hi",function(){
  bsync({request:bp.Event("hello")});
})

bp.registerBThread("hello world Patch", function(){
  bsync({waitFor:bp.Event("hello"), block:bp.Event("world")});
})