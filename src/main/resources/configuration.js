rule({
	when: function(ctx) { 
		return true 
	},
	then: function(ctx) {
		ctx.node("orderType").value = "service";
		ctx.node("service.type").value = "voice";
		ctx.node("service.voice.application").value = "ip";
	}
});

rule({ 
	when: function(ctx) { 
		return ctx.node("service.voice.application").value == "ip" 
    },
	then: function(ctx) {
		ctx.node("product.phone.model").enable("ipStandard", "ipExecutive");
	}
});
