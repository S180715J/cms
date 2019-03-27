function myMenu(id) {
	$(id).contextMenu({
				width: 110, // width
				itemHeight: 30, // 菜单项height
				bgColor: "#333", // 背景颜色
				color: "#fff", // 字体颜色
				fontSize: 12, // 字体大小
				hoverBgColor: "#99CC66", // hover背景颜色
				target: function(ele) { // 当前元素
					console.log(ele.html());
				},
				menu: [{ // 菜单项
						text: "删除",
						icon: "img/add.png",
						callback: function() {
							alert("新增");
						}
					},
					{
						text: "复制到",
						icon: "img/copy.png",
						callback: function() {
							$(this).contextMenu({
								width: 110, // width
				itemHeight: 30, // 菜单项height
				bgColor: "#333", // 背景颜色
				color: "#fff", // 字体颜色
				fontSize: 12, // 字体大小
				hoverBgColor: "#99CC66", // hover背景颜色
				target: function(ele) { // 当前元素
					console.log(ele);
				},
				menu:[{
					text:'你的',
					callback:function () {
						 alert('你的');
					}
				},{
					text:'我的',
					callback:function () {
						alert('我的');
					}
				}
				]
							});
							alert("复制");
						}
					},
					{
						text: "粘贴",
						icon: "img/paste.png",
						callback: function() {
							alert('粘贴');
						}
					},
					{
						text: "移动到",
						icon: "img/del.png",
						callback: function() {
							alert("删除");
						}
					}
				]

			});
}