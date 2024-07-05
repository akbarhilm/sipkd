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


function grid( ) {
    var urljson = getbasepath() + "/spmpotayat/json/listpotayatjson?nospm=" + $("#nospm").val();
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
                var nilaipot = accounting.formatNumber(aData['nilaiPot'], 2, '.', ",");
                var cpot = "<input type='hidden' name='cpot" + aData['idPot'] + "' id='cpot" + aData['idPot'] + "' value='" + aData['cPot'] + "'>";
                var status = "<input type='hidden' name='statuss" + aData['idPot'] + "' id='statuss" + aData['idPot'] + "' value='" + aData['status'] + "'>";
                var nilai;
                var kodeEdit = "<input type='hidden' name='addoredit" + index + "' id='addoredit" + index + "' value='" + aData['kodeEdit'] + "'>";


                $('td:eq(0)', nRow).html(index + kodeEdit);

                if (index == 100) { // if (index == 9){
                    var asuransi = "<input type='text' id='asuransi' name='asuransi' size = '15' value ='" + aData['persen'] + "' class='inputnumber' onchange='hitungasuransi()' onkeyup='hitungasuransi()' onkeydown='validateNumber(this.input)'> x Rp 20.000 </>";
                    $('td:eq(2)', nRow).html(asuransi);
                    nilai = "<input type='text' name='isi" + aData['idPot'] + "' id='isi" + aData['idPot'] + "' value='" + nilaipot + "'  class='inputmoney' readonly='true'> ";

                } else {
                    $('td:eq(2)', nRow).html("");
                    nilai = "<input type='text' name='isi" + aData['idPot'] + "' id='isi" + aData['idPot'] + "' value='" + nilaipot + "'  class='inputmoney'>";
                }

                $('td:eq(3)', nRow).html(cpot + status + nilai);
                //$('td:eq(3)', nRow).html(cpot+status+"<input type='text' name='isi" + aData['idPot'] + "' id='isi" + aData['idPot'] + "' value='" + nilaipot + "'  class='inputmoney'>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPot", "bSortable": false, sClass: "center"},
                {"mDataProp": "pot", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "idPot", "bSortable": false, sClass: "center"},
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



function submitnilai( ) {
    var asuransi = $("#asuransi").val();

    var varurl = getbasepath() + "/spmpotayat/json/prosespindahsimpan";
    var dataac = [];
    var datapeg = {
        cpot1: $("#cpot1").val(),
        cpot2: $("#cpot2").val(),
        cpot3: $("#cpot3").val(),
        cpot4: $("#cpot4").val(),
        cpot5: $("#cpot5").val(),
        cpot6: $("#cpot6").val(),
        cpot7: $("#cpot7").val(),
        cpot8: $("#cpot8").val(),
        cpot9: $("#cpot9").val(),
        vpot1: $("#isi1").val(),
        vpot2: $("#isi2").val(),
        vpot3: $("#isi3").val(),
        vpot4: $("#isi4").val(),
        vpot5: $("#isi5").val(),
        vpot6: $("#isi6").val(),
        vpot7: $("#isi7").val(),
        vpot8: $("#isi8").val(),
        vpot9: $("#isi9").val(),
        statuss1: $("#statuss1").val(),
        statuss2: $("#statuss2").val(),
        statuss3: $("#statuss3").val(),
        statuss4: $("#statuss4").val(),
        statuss5: $("#statuss5").val(),
        statuss6: $("#statuss6").val(),
        statuss7: $("#statuss7").val(),
        statuss8: $("#statuss8").val(),
        statuss9: $("#statuss9").val(), // sengaja, karena cpot 13 status nya ga ke-sum
        addoredit1: $("#addoredit1").val(),
        addoredit2: $("#addoredit2").val(),
        addoredit3: $("#addoredit3").val(),
        addoredit4: $("#addoredit4").val(),
        addoredit5: $("#addoredit5").val(),
        addoredit6: $("#addoredit6").val(),
        addoredit7: $("#addoredit7").val(),
        addoredit8: $("#addoredit8").val(),
        addoredit9: $("#addoredit9").val(),
        asuransi: asuransi,
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
        bootbox.alert("Data SPM Potongan Ayat berhasil disimpan");
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

function hitungasuransi() {
    var cpot = $("#cpot13").val();
    var jumlah, banyak;
    banyak = $("#asuransi").val();
    console.log("cpot ==== " + cpot);
    jumlah = (banyak * 20000);

    $('#isi13').val(accounting.formatNumber(jumlah, 2, '.', ","));

}

function validateNumber(evt) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
            // numbers   
            key >= 48 && key <= 57 ||
            // Numeric keypad
            key >= 96 && key <= 105 ||
            // Backspace and Tab and Enter
            key == 8 || key == 9 || key == 13 ||
            // Home and End
            key == 35 || key == 36 ||
            // left and right arrows
            key == 37 || key == 39 ||
            // Del and Ins
            key == 46 || key == 45) {
        // input is VALID
    }
    else {
        // input is INVALID
        e.returnValue = false;
        if (e.preventDefault)
            e.preventDefault();
    }
}