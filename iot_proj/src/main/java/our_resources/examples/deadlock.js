bp.registerBThread("orders", function(){
	bp.log.info("found: shabat");
    bsync({waitFor:bp.Event("shabat")});
    bsync({request:bp.Event("shalom")});
});

bp.registerBThread("coffee supply", function(){
	bp.log.info("found: shalom");
		bsync({waitFor:bp.Event("shalom")});
      bp.log.info("found: mevorach");
		bsync({waitFor:bp.Event("mevorach")});
});
