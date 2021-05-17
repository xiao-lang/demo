Ext.namespace("testFile");
testFile.ra = Ext.extend(Object, {
    contructor: function (config) {
        this.id = Ext.id(null, 'ext-layout-');
        Ext.apply(this, config);
    },
    getWin: function () {
        return Ext.getCmp("test_id");

    },
    grid_sm : function(){
        return new  Ext.selection.CheckboxModel({});
    },

    grid_store : function(){
        var _this = this;
        return new Ext.data.JsonStore({
             totalProperty : 'totalCount',
             id : 'grid_storeId',
             proxy: {
                type: 'ajax',
                url: '../test/helloTest2',
                reader: {
                    type: 'json',
                    root: 'root',
                },
                 fields : [ {
                     name : 'testId'
                 }, {
                     name : 'testName'
                 }, {
                     name : 'testAge'
                 }, {
                     name : 'testSex'
                 }, {
                     name : 'testBirthday'
                 }, {
                     name : 'testDate'
                 }]
            },
        });
    },
    /*grid_cm : function(){
        var _this = this;
        var g =  new Ext.grid.column.Column([new Ext.grid.RowNumberer({}),{
            header: 'userId',
            // hidden:true,
        },{
            header: '姓名',
        },{
            header: '年龄',
        },{
            header: '性别',
        },{
            header: '生日',
        }]);
        return g;
    },*/

    wllbGrid: function () {
        var _this = this;
        var _sn = _this.grid_sm();
        var $store = _this.grid_store();
        var g = new Ext.grid.Panel({
            id: 'gridId',
            width: '100%',
            height: '100%',
            border: false,
            store:$store,
            selModel : _sn,
            // store: Ext.create('Ext.data.ArrayStore', {})
            columns: [new Ext.grid.RowNumberer({}),{
                header: 'userId',
                dataIndex : 'testId',
                hidden:true,
            },{
                header: '姓名',
                dataIndex : 'testName',
                sortable : true,
            },{
                header: '年龄',
                dataIndex : 'testAge',
                sortable : true,
            },{
                header: '性别',
                dataIndex : 'testSex',
                sortable : true,
            },{
                header: '生日',
                dataIndex : 'testBirthday',
                sortable : true,
            },{
                header: '操作',

            }]    // 仅仅用来显示一个头部。没有数据，
            // columns: _this.grid_cm()
        });
        return g;
    },


    win: function () {
        var _this = this;
        var w = _this.getWin();
        if (w == null) w = new Ext.Window({
            title: "测试框",
            id: 'test_id',
            width: '100%',
            height: '100%',
            items: _this.wllbGrid(),
            buttons: [{
                text: "确定",
            }, {
                text: "关闭",
                handler:function () {
                    w.close();
                }
            }]
        });
        return w;
    },
});
Ext.onReady(function () {
    var a = new testFile.ra();
    a.win().show();
});