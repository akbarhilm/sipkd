$(document).ready(function() {
    gettree();
});


function gettree() {
    $('#jstree_skpd').fancytree({
        title: "Daftar SKPD",
        extensions: ["table"],
        table: {
            indentation: 20, // indent 20px per node level
            nodeColumnIdx: 1, // render the node title into the 2nd column
            checkboxColumnIdx: 0  // render the checkboxes into the 1st column
        },
        checkbox: true, // Show checkboxes.
        selectMode: 1, // 1:single, 2:multi, 3:multi-hier
        fx: {height: "toggle", duration: 200}, // Animations, e.g. null or { height: "toggle", duration: 200 }
        keyboard: true,
        source: {
            url: getbasepath() + '/refskpd/json/listdataskpdrootjson',
            data: {key: "1"},
            cache: false
        },
        lazyLoad: function(event, data) {
            var node = data.node;


            console.log("lazy loading ");
            //data.node.load(true);             
            // Issue an ajax request to load child nodes
            data.result = {cache: false, url: getbasepath() + '/refskpd/json/listdataskpdanakjson', data: {id: node.key}};
        },
        renderColumns: function(event, data) {
            var node = data.node, $tdList = $(node.tr).find(">td");
            var isceked = node.data.isaktif == '1'?'checked':'';
            var cekaktif = "<input type='checkbox' name='isAktif"+ node.key +"' id='isAktif"+ node.key +"' disabled  "+isceked+" />";
            console.log(node);
            $tdList.eq(2).text(node.data.kodeskpd);
            $tdList.eq(3).text(node.data.kodeunit);
            $tdList.eq(4).html(cekaktif);
            

        }, click: function(event, data) {


        },
        select: function(event, data) {
            // var s = data.tree.getSelectedNodes().join(", ");
            $("#keyalamat").val(data.node.key);
        }

    });

}
function pindahhaledit() {
    var keyalamat = $("#keyalamat").val();
    if (keyalamat !== '') {
        $("#popupeditskpd").prop("href",getbasepath() + "/refskpd/editskpd/" + keyalamat);
        $("#popupeditskpd").click();

    } else {
        bootbox.alert("Skpd yang akan di ubah wajib dipilih");
    }
}
function pindahhalinsert() {
    var keyalamat = $("#keyalamat").val();
    if (keyalamat !== '') {
        $("#popuptambahskpd").prop("href",getbasepath() + "/refskpd/addskpd/" + keyalamat);
        $("#popuptambahskpd").click();

    } else {
        bootbox.alert("Skpd yang akan ditambah wajib pilih induk");
    }
}



function pindahhalcetakskpd() {
     var keyalamat = $("#keyalamat").val();
    if (keyalamat !== '') {
        $("#popupcetakskpd").prop("href",getbasepath() + "/cetaksipkd/reports/pdf/" + keyalamat);
        $("#popupcetakskpd").click();

    } else {
        bootbox.alert("Skpd yang akan dicetak wajib di pilih ");
    }
  
}
