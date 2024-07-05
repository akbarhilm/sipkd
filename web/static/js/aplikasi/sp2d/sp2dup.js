/*$(document).ready(function () {
 var timer = $.timer(function () {
 var idskpd = $("#idskpd").val();
 var level = $("#level").val();
 if (idskpd && idskpd != 'undefined' && $.trim(idskpd).length > 0) {
 gridsppup();
 }
 });
 // timer.set({time: 120000, autostart: true});
 });*/

function gridsppup() {
    var baseurl = getbasepath()
    var urljson = baseurl + "/sp2dsah/json/getlistsp2d";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()},
                {name: "kproses", value: $('#kproses').val()}
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
                var lskpd = $('#skpd').val();
                var level = $('#levelSkpd').val();
                var objar = lskpd.split("/");
                var ls = objar[2];
                var tahun = $('#tahun').val();
                var kj = aData['kodeJenis'];
                var c = aData['kodeJenis'];
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";
                var namafile = "SP2D" + "-" + kj + "-" + aData['kodeBeban'] + "-" + tahun + "-" + aData['sp2d']['nomorSp2d'] + ".pdf";
                var stat = aData['sp2d']['kodeCetak'];

                var nilaisp2d = "<input type='hidden'    id='nilaisp2d" + aData['id'] + "' value='" + aData['sp2d']['nilaiSp2d'] + "'  />";
                var nomorsp2d = "<input type='hidden'    id='nomorsp2d" + aData['id'] + "' value='" + aData['sp2d']['nomorSp2d'] + "'  />";

                var idsp2d = "<input type='hidden' id='idsp2d" + aData['id'] + "' value='" + aData['id'] + "'  />";
                var idspp = "<input type='hidden' id='idspp" + aData['id'] + "' value='" + aData['sp2d']['idSpp'] + "'  />";
                var kodeumk = "<input type='hidden' id='kodeumk" + aData['id'] + "' value='" + aData['sp2d']['kodeUmk'] + "'  />";
                var beban = "<input type='hidden' id='beban" + aData['id'] + "' value='" + aData['kodeBeban'] + "' />";
                var jenis = "<input type='hidden' id='jenis" + aData['id'] + "' value='" + aData['kodeJenis'] + "' />";
                var idkontrak = "<input type='hidden' id='idkontrak" + aData['id'] + "' value='" + aData['sp2d']['idKontrak'] + "' />";

                //var donlod = aData['status'] == 'CETAK' || aData['status'] == 'VALIDASI' ? "<a href='" + getbasepath() + "/cetaksp2d/" + aData['kodeBeban'] +"/" + aData['id'] + "/" + aData['noSp2d'] + "' class='fancybox fancybox.iframe icon-book' ></a>" : '';

                $('td:eq(0)', nRow).html(index + idsp2d + idspp + kodeumk + beban + jenis + idkontrak);
                $('td:eq(1)', nRow).html(aData['sp2d']['nomorSp2d'] + nomorsp2d + nilaisp2d);
                $('td:eq(2)', nRow).html(getTanggal(aData['sp2d']['tglSp2d']));
                $('td:eq(3)', nRow).html(aData['kodeBeban']);
                $('td:eq(4)', nRow).html(aData['kodeJenis']);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sp2d']['nilaiSp2d']));

                var namaPptk = "<input type='hidden'    id='namaPptk" + aData['id'] + "' value='" + aData['namaPptk'] + "'  />";
                var nipPptk = "<input type='hidden'    id='nipPptk" + aData['id'] + "' value='" + aData['nipPptk'] + "'  />";


                $('td:eq(6)', nRow).html(aData['sp2d']['kodeCetak']);
                $('td:eq(7)', nRow).html("<span id='namaPptk" + aData['id'] + "'>" + aData['namaPptk'] + nipPptk + "</span>");
                //$('td:eq(10)', nRow).html(aData['status_cetakpotongan']);tmsp2d.c_sp2d_cetak
                if (stat == 'CETAK') {
                    var cekprint = "<input type='radio' name='cek'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
                }

                $('td:eq(8)', nRow).html(cekprint);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "sp2d.nomorSp2d", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.tglSp2d", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeBeban", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "kodeJenis", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "sp2d.nilaiSp2d", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "sp2d.kodeCetak", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}

            ]
        });
        // $("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Sah</span>");
        $("div.top").html("<button id='prosespengesahan' onclick=cekUmkSah() class='btn .dark-stripe' style='float: right'>Proses Pengesahan</button>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function cekUmkSah() {

    $('.checkbox:checked').each(function() {
        var id = $(this).val();
        var kodeUmk = $("#kodeumk" + id).val();
        var beban = $("#beban" + id).val();
        var jenis = $("#jenis" + id).val();
        var idkontrak = $("#idkontrak" + id).val();

        console.log("kode Umk = " + kodeUmk);
        console.log("beban = " + beban);
        console.log("jenis = " + jenis);
        console.log("id kontrak = " + idkontrak);

        if (beban == "LS" && jenis == "BL") { // KHUSUS LS-BL
            if (kodeUmk == "0") {
                console.log("masuk kode umk = 0");
                getUmkSah(idkontrak);
            } else { // JIKA SPP SEBAGAI UMK
                submitvalidasi();
            }

        } else {
            submitvalidasi();
        }

    });
}

function getUmkSah(idkontrak) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/sp2dsah/json/getUmkSah", {tahun: tahun, idkontrak: idkontrak, idskpd: idskpd},
    function(result) {
        var umksah = result.aData['kodePengesahan'];
        var saldoUmk = result.aData['saldoUmk'];
        var kodeMY = result.aData['kodeMY'];

        // harusnya dicek: kontraknya ada

        if (saldoUmk > 0) { // JIKA KONTRAK TSB PUNYA POT UMK
            if (kodeMY == "2") {  // JIKA BUKAN MULTIYEAR; KL MULTIYEAR kodeMY = 1, JIKA TIDAK ADA UMK MAKA kodeMY = 0
                if (umksah == "0") {
                    bootbox.alert("SPM UMK Belum Validasi SP2D, Silahkan Validasi SPM UMK Terlebih Dulu");
                } else {
                    submitvalidasi();
                }
            } else {
                submitvalidasi();
            }
        } else {
            submitvalidasi();
        }


    });

}

function submitvalidasi() {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
    var statuscek = 0;
    var arrnospd = [];
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
        var nilai = $("#nilaisp2d" + idspd).val();

        selected.push({"idspd": idspd, "nilaisp2d": nilai});

        var nospd = $("#nomorsp2d" + idspd).val();
        selectednoskpd.push(idspd);
        arrnospd.push(nospd);

    });


    bootbox.confirm("Anda akan melakukan pengesahan untuk SP2D dengan nomor " + arrnospd.join(",") + " di" + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return      $.ajax({
                type: "POST",
                url: baseurl + "/sp2dsah/json/updatesp2dsah",
                contentType: "text/plain; charset=utf-8",
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(selected),
                beforeSend: function() {
                    console.log(" before submit " + new Date())

                }
            }).always(function(data) {

                gridsppup();
                bootbox.alert(data.responseText);
                //--print
                selectednoskpd.forEach(function(idspd) {
                    //alert(nospd);
                    printUrl(idspd);
                });

            });
        } else {
            //alert("fsdfsdf");
            bootbox.hideAll();
            return result;
        }
    });

}

function printUrl(idspd) {
    var url = getbasepath() + "/sp2dsah/printvalidasi/cetakpengesahan/" + idspd;
    printPage(url)

}
function closePrint() {
    document.body.removeChild(this.__container__);
}

function setPrint() {
    this.contentWindow.__container__ = this;
    this.contentWindow.onbeforeunload = closePrint;
    this.contentWindow.onafterprint = closePrint;
    this.contentWindow.print();
}

function printPage(sURL) {
    var oHiddFrame = document.createElement("iframe");
    oHiddFrame.onload = setPrint;
    oHiddFrame.style.visibility = "hidden";
    oHiddFrame.style.position = "fixed";
    oHiddFrame.style.right = "0";
    oHiddFrame.style.bottom = "0";
    oHiddFrame.src = sURL;
    document.body.appendChild(oHiddFrame);
}


function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();

        var namaPptk = $("#namaPptk" + idspd).text();
        var nipPptk = $("#nipPptk" + idspd).val();
        //alert(idspd);
        //alert(namaPptk);

        if (namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1) {
            statuscek++;
        } else {
            var map = {"id": idspd,
                "namaPptk": namaPptk,
                "nipPptk": nipPptk
            }
            selected.push(map);
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/sp2dsah/json/updatesp2dsah",
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(selected)
        }).always(function(data) {
            gridsppup();
            bootbox.alert(data.responseText);
        });
    } else {
        bootbox.alert("Pejabat penandatangan wajib dipilih sebelum cetak!");
    }
}


function batalspd(idspd, nosp2d) {
    var urlhapusspd = getbasepath() + "/cetaksp2d/json/hapussp2dcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SP2D dengan nomor " + nosp2d + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospp": nosp2d})
            }).always(function(data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}