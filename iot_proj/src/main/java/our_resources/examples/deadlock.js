bp.registerBThread("orders", function(){
	bp.log.info("found: shabat");
    bsync({waitFor:bp.Event("shabat")});
    bsync({request:bp.Event("shalom")});
});

bp.registerBThread("coffee supply", function(){
      bsync({waitFor:bp.Event("shalom")});
      bsync({request:bp.Event("mevorach")});
});
