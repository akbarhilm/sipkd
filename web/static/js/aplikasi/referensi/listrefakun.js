$(document).ready(function() {
    gettree();

});

function ubahakun() {
    var koderekening = document.getElementById("kodeRekening");
    var kodeakun = document.getElementById("kodeAkun");
    var kodeakunkelompok = document.getElementById("kodeAkunKelompok");
    var kodeakunjenis = document.getElementById("kodeAkunJenis");
    var kodeakunobjek = document.getElementById("kodeAkunObjek");
    var kodeakunrincian = document.getElementById("kodeAkunRincian");
    var kodeakunsubrincian = document.getElementById("kodeAkunSubRincian");
    var kode = koderekening.value;
    var subrincian = kodeakunsubrincian.value;
    var akun = kodeakun.value;
    if (subrincian = akun) {
        kodeakunrincian.value = kode;
        kodeakunobjek.value = kode;
        kodeakunjenis.value = kode;
        kodeakunsubrincian.value = kode;
    }

}
function gettree() {
    $('#jstree_akun').fancytree({
        title: "Daftar Akun",
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
            url: getbasepath() + '/refakun/json/listdataakunrootjson',
            data: {key: "1"},
            cache: false
        },
        lazyLoad: function(event, data) {
            var node = data.node;
            // console.log(node.key);
            //data.node.load(true);             
            // Issue an ajax request to load child nodes
            var leval = node.data.lvl;
            var kode = node.data.kode;
            data.result = {cache: false, url: getbasepath() + '/refakun/json/listdataakunanakjson', data: {id: node.key}};
        },
        renderColumns: function(event, data) {
            var node = data.node, $tdList = $(node.tr).find(">td");
            var Pilih = "<a class='icon-edit' href='" + getbasepath() + "/refskpd/editskpd/" + node.key + "'  ></a> ";
            var isceked = node.data.isaktif == '1' ? 'checked' : '';
            var cekaktif = "<input type='checkbox' name='isaktif" + node.key + "' id='isaktif" + node.key + "' disabled  " + isceked + " />";

            console.log(node);
            $tdList.eq(2).html(cekaktif);
            /* var node = data.node, $tdList = $(node.tr).find(">td");
             var pilih = "<a class='icon-edit'  id='pilih" + node.key + "' href='javascript: void(0)' ></a>";
             $tdList.eq(2).html(pilih);*/
        }, click: function(event, data) {


        },
        select: function(event, data) {
            // var s = data.tree.getSelectedNodes().join(", ");
            $("#keyalamat").val(data.node.key)
        }
    });

}
function pindahhal() {
    var keyalamat = $("#keyalamat").val();
    if (keyalamat !== '') {
        $("#popupubahakun").prop("href", getbasepath() + "/refakun/index/updateakun/" + keyalamat);
        $("#popupubahakun").click();
        // window.location.replace(getbasepath() + "/refakun/index/updateakun/" + keyalamat);
    } else {
        bootbox.alert("Akun yang akan di ubah wajib dipilih");
    }
}
function pindahhaltambah() {
    var keyalamat = $("#keyalamat").val();
    if (keyalamat !== '') {
        $("#popuptambahakun").prop("href", getbasepath() + "/refakun/index/tambahakun/" + keyalamat);
        $("#popuptambahakun").click();

        // window.location.replace(getbasepath() + "/refakun/index/updateakun/" + keyalamat);
    } else {
        bootbox.alert("Akun yang akan ditambah wajib pilih induk");
    }
}
function pindahhalcetak() {
    window.location.replace(getbasepath() + "/cetakakun/reports/pdf");
}


function pindahhalcetakakun() {
    var keyalamat = $("#keyalamat").val();
    if (keyalamat !== '') {
        $("#popupcetakakun").prop("href", getbasepath() + "/cetakakun/reports/pdf/" + keyalamat);
        $("#popupcetakakun").click();

    } else {
        bootbox.alert("Akun yang akan dicetak wajib di pilih ");
    }
}