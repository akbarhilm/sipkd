$(document).ready(function() {
    setjumkotpot();
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

var banyakdata;

function toval(datake) {
    var nilaidata = accounting.unformat($('#isi' + datake).val(), ",");
    $('#isi' + datake).val(accounting.formatNumber($(nilaidata).val()));
}


function grid() {
    var urljson = getbasepath() + "/spmpotayat/json/listpotayatgajijson?nospm=" + $("#nospm").val();
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
                //$(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idspp", value: $('#idspp').val()},
                        {name: "kodeSimpeg", value: $('#kodesimpeg').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                //var pageTotal = 0;
                console.log("tes "+nRow+" = "+iStart+" = "+iEnd+" = "+iDisplay);
                var spmpottotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        //pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                        spmpottotal += parseFloat(aaData[i]['nilaiPot']);
                        console.log("tes "+i);
                    }
                }

                //$("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                $("#totalpotspm").val(accounting.formatNumber(spmpottotal, 2, '.', ","));
                //banyakBarisGrid = aaData.length;
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
                banyakdata = index;
                var nilaipot = aData['nilaiPot'];//accounting.formatNumber(aData['nilaiPot'], 2, '.', ",");
                var cpot = "<input type='hidden' name='cpot" + index + "' id='cpot" + index + "' value='" + aData['cPot'] + "'>";
                //var idtmpot = "<input type='hidden' name='idtmpot" + index + "' id='idtmpot" + index + "' value='" + aData['idTmPotongan'] + "'>";
                var nilai = "<input type='text' name='isi" + index + "' id='isi" + index + "' value='" + nilaipot + "'  class='inputmoney' onkeyup='getTotalNilaiPotongan()' title='"+accounting.formatNumber(aData['nilaiPot'], 2, '.', ",")+"'>";
                var kodeEdit = "<input type='hidden' name='addoredit" + index + "' id='addoredit" + index + "' value='" + aData['kodeEdit'] + "'>";


                $('td:eq(0)', nRow).html(index + kodeEdit);
                $('td:eq(2)', nRow).html("");
                $('td:eq(3)', nRow).html(cpot + nilai);
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
    var varurl = getbasepath() + "/spmpotayat/json/simpanpotgaji";
    var dataac = [];
    var isilist = [];
    for (i = 0; i < banyakdata; i++) { // list
        var id = i + 1;

        var pararr = {
            cpot: $("#cpot" + id).val(),
            isi: $("#isi" + id).val(),
            addoredit: $("#addoredit" + id).val()
        };
        isilist[i] = pararr;
    }
    var datapeg = {
        idspm: $("#nospm").val(),
        idskpd: $("#idskpd").val(),
        tahun: $("#tahunAnggaran").val(),
        isilist:isilist
    }
    dataac = datapeg;
    
    var totalpotongangrid = parseFloat(accounting.unformat($("#totalpotspm").val(), ","));
    var totalpotongangaji = parseFloat($("#sspotspm").val());
    if (totalpotongangrid > totalpotongangaji){
        bootbox.alert("Total Nilai tidak boleh melebihi Total Potongan.");
    }
    else {
        //bootbox.alert("Bisa disimpan");
        
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

function setjumkotpot(){
    var totalpotongan = $("#sspotspm").val();
    $("#sipotspm").val(accounting.formatNumber(totalpotongan, 2, '.', ","));
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

function getTotalNilaiPotongan() {
    var i;
    var totalPotongan = 0;
    for (i = 0; i < banyakdata; i++) { // list
        var id = i + 1;
        totalPotongan += parseFloat(accounting.unformat($("#isi"+id).val(), ","));
    }
    $("#totalpotspm").val(accounting.formatNumber(totalPotongan, 2, '.', ","));
}