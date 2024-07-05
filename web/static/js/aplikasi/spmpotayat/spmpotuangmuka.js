$(document).ready(function() {
    settgl();
    grid();
    $(".fancybox").fancybox({
        fitToView: true,
        autoSize: true,
        closeClick: true,
        openEffect: 'swing',
        closeEffect: 'swing',
        headers: {'X-fancyBox': true}
    });
});


function toval(datake) {
    var nilaidata = accounting.unformat($('#isi' + datake).val(), ",");
    $('#isi' + datake).val(accounting.formatNumber($(nilaidata).val()));
}


function grid() {
    var urljson = getbasepath() + "/spmpotuangmuka/json/listpotjson?nospm=" + $("#nospm").val();
    $("#btlspdtablebody").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "spm", value: $('#spm').val()}
                );
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var pilihan = "";
                var idspm = $('#nospm').val();
                var idspp = $('#idspp').val();
                var idkontrak = $('#idkontrak').val();

                var nilaipot = accounting.formatNumber(aData['nilaiPot'], 2, '.', ",");
                var cpot = "<input type='hidden' name='cpot" + aData['idPot'] + "' id='cpot" + aData['idPot'] + "' value='" + aData['cPot'] + "'>";
                var status = "<input type='hidden' name='statuss" + aData['idPot'] + "' id='statuss" + aData['idPot'] + "' value='" + aData['status'] + "'>";
                var kodeEdit = "<input type='hidden' name='addoredit" + index + "' id='addoredit" + index + "' value='" + aData['kodeEdit'] + "'>";
                var inputpot;
                var umk = "<a id ='inputumk' href='#' class='fancybox fancybox.iframe btn blue' title='Input Potongan UMK' >Input Potongan UMK</a>";
                //var umk = "<a href='" + getbasepath() + "/spmpotuangmuka/addpotonganumk/" + idspp + "/" + idspm + "/" + idkontrak + "'' class='fancybox fancybox.iframe btn blue' title='Input Potongan UMK' >Input Potongan UMK </a>";
                var akundenda = "<select id='akun" + aData['idPot'] + "' name='akun" + aData['idPot'] + "' style='width: 500px;' onchange='' ><option></option></select>";

                if (aData['idPot'] == "31") {
                    if ($('#kodeumk').val() == "1") { // jika ga ada pot UMK tapi ada pot ayat
                        pilihan = "<a id ='inputumk' onclick='ambilidata()' class='btn blue' href='" + getbasepath() + "/spmpotuangmuka/addpotonganumk' >Input Potongan UMK</a>";
                    } else {
                        pilihan = "";
                    }
                    
                    inputpot = "<input type='text' name='isi" + aData['idPot'] + "' id='isi" + aData['idPot'] + "' value='" + nilaipot + "'  class='inputmoney' readOnly='true' >";
                    console.log("kode pot umk ===   "+$('#kodeumk').val());
                } else {
                    inputpot = "<input type='text' name='isi" + aData['idPot'] + "' id='isi" + aData['idPot'] + "' value='" + nilaipot + "'  class='inputmoney'>";
                }

                if (aData['idPot'] == "33" || aData['idPot'] == "34") {
                    pilihan = akundenda;
                    setAkunDenda(aData['idPot']);
                }

                $('td:eq(0)', nRow).html(index + kodeEdit);
                $('td:eq(2)', nRow).html(pilihan);
                $('td:eq(3)', nRow).html(cpot + status + inputpot);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPot", "bSortable": false, sClass: "center"},
                {"mDataProp": "pot", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "pot", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "idPot", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function ambilidata() {
    var idspm = $('#nospm').val();
    var idspp = $('#idspp').val();
    var idkontrak = $('#idkontrak').val();
    window.localStorage.setItem("idspmval", idspm);
    window.localStorage.setItem("idsppval", idspp);
    window.localStorage.setItem("idkontrakval", idkontrak);

}

function submitnilai() {
    //alert($("#cpot1").val());
    var varurl = getbasepath() + "/spmpotuangmuka/json/prosespindahsimpan";
    var dataac = [];
    var datapeg = {
        cpot1: $("#cpot25").val(),
        cpot2: $("#cpot26").val(),
        cpot3: $("#cpot31").val(),
        cpot4: $("#cpot32").val(),
        cpot5: $("#cpot33").val(),
        cpot6: $("#cpot34").val(),
        cpot7: $("#cpot35").val(),
        cpot8: $("#cpot36").val(),
        cpot9: $("#cpot37").val(),
        vpot1: $("#isi25").val(),
        vpot2: $("#isi26").val(),
        vpot3: $("#isi31").val(),
        vpot4: $("#isi32").val(),
        vpot5: $("#isi33").val(),
        vpot6: $("#isi34").val(),
        vpot7: $("#isi35").val(),
        vpot8: $("#isi36").val(),
        vpot9: $("#isi37").val(),
        statuss1: $("#statuss25").val(),
        statuss2: $("#statuss26").val(),
        statuss3: $("#statuss31").val(),
        statuss4: $("#statuss32").val(),
        statuss5: $("#statuss33").val(),
        statuss6: $("#statuss34").val(),
        statuss7: $("#statuss35").val(),
        statuss8: $("#statuss36").val(),
        statuss9: $("#statuss37").val(),
        addoredit1: $("#addoredit1").val(),
        addoredit2: $("#addoredit2").val(),
        addoredit3: $("#addoredit3").val(),
        addoredit4: $("#addoredit4").val(),
        addoredit5: $("#addoredit5").val(),
        addoredit6: $("#addoredit6").val(),
        addoredit7: $("#addoredit7").val(),
        addoredit8: $("#addoredit8").val(),
        addoredit9: $("#addoredit9").val(),
        akun33: $("#akun33").val(),
        akun34: $("#akun34").val(),
        idspm: $("#nospm").val()
    }
    dataac = datapeg;

    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        grid();
        bootbox.alert("Data SPM Potongan Uang Muka berhasil disimpan");
    });
}

function settgl() {
    var isi = $("#ssspm").val();
    var tahun = isi.substr(0, 4);
    var bulan = isi.substr(5, 2);
    var hari = isi.substr(8, 2);
    var hasil = hari + "/" + bulan + "/" + tahun;

    $("#sispm").val(hasil);
    // alert("#sspm");
}

function setAkunDenda(id) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idspm = $('#nospm').val();

    $.getJSON(getbasepath() + "/spmpotuangmuka/json/getAkunDenda", {tahun: tahun, idskpd: idskpd, idspm: idspm},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        try {
            if (banyak > 0) {

                for (var i = 0; i < banyak; i++) {
                    kode = result.aData[i]['idBas'];
                    ket = result.aData[i]['namaAkun'];

                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                }

                $("#akun" + id).html(opt);
            }
        } catch (e) {
            console.log(e);
        }
    });
}