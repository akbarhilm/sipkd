$(document).ready(function() {


    setPanel();
    //getKodeTutup();
    gridspj();
    $("#sumspj").val(0);
});

// global variable
var jumbarisspj;

function setPanel() {
    var beban = $('#beban').val();
    var grid = document.getElementById('spjtable');

    if (beban == "TU") {
        grid.rows[0].cells[4].innerText = 'Nilai SPP TU';
        grid.rows[0].cells[6].innerText = 'Sisa SPP TU';
        document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan TU (Kas SKPD) :';
        document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai BKU TU (4) :';
        $('#bebanspj').val("TU");

    } else if (beban == "UP") {
        grid.rows[0].cells[4].innerText = 'Nilai LS (BAST)';
        grid.rows[0].cells[6].innerText = 'Sisa SPD';
        document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan UP/GU (Kas SKPD) :';
        document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai SPP TU (4) :';
        $('#bebanspj').val("UP/GU");
    }
}

function gridspj() {
    jumbarisspj = 0;
    var nobukti = $("#noBukti").val();

    if (nobukti == "") {
        nobukti = " ";
    }

    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbkuspj",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "nobku", value: $("#noBKU").val()},
                {name: "beban", value: $("#beban").val()},
                {name: "nobukti", value: nobukti}
                );

            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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
                var isceked = aData['nilaiBkuInput'] > 0 ? 'checked' : '';
                var readonlyinput = aData['nilaiBkuInput'] > 0 ? "" : "readonly";
                var nilaipenanda = aData['nilaiBkuInput'] > 0 ? 1 : 0;

                var total = parseInt($("#totalspjhidden").val());

                if (parseInt(aData['nilaiBkuInput']) > 0) {
                    total = total + parseInt(aData['nilaiBkuInput']);
                    $("#totalspjhidden").val(total);
                    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
                    //console.log("2 total = "+total);
                }

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + nilaipenanda + "' />";
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisp2d = "<input type='text' name='nilaisp2d" + index + "' id='nilaisp2d" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSp2d'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' readOnly='true'   />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaakun'] + "</textarea>";
                var textnamaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                jumbarisspj = jumbarisspj + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(nilaiAngg);
                $('td:eq(4)', nRow).html(nilaisp2d);
                $('td:eq(5)', nRow).html(nilaisebelum);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(nilaiinput);
                //$('td:eq(8)', nRow).html(inputcek + idbas + textnamaakun + textkodeakun + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left", "sWidth": "9%"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left", "sWidth": "22%"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "nilaiBkuSp2d", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right", "sWidth": "13%"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right", "sWidth": "13%"}
                //{"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "3%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

    getNilaiSisaSpj();
    getNilaiValidasiSisaSpj();
    $("#sumspj").val(0);
    $("#totalspjhidden").val(0);
    hitungnilaispj();
}

function enablerow(obj, index) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + index).val(1);
    } else {
        $("#penanda" + index).val(0);
    }
    setdisabled(param, index);
    hitungnilaispj();
}
function setdisabled(param, index) {
    $("#nilaiinput" + index).attr("readonly", param);

}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai BKU tidak boleh lebih besar dari Sisa BKU.");
        // $('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', $("#nilaisisa" + index).val());
    }

    hitungnilaispj();

}

function setNamaAkunEdit(textid) {
    var id = textid.substring(4);
    var idbas = $("#akun" + id).val();
    $("#namaakun" + id).val(akunnama[idbas]);
}

function getKegiatan() {
    $('#idKegiatan').val($('#idKegpop').val());
    $("#ketKegiatan").val($('#ketKegpop').val());
    $("#kodeKeg").val($('#kodeKegpop').val());

    gridspj();
}

function update() {
    var tglPost = $("#tglPosting").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var bulan = $("#bulan").val();

    if (bulanTglPost !== bulan) {
        bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
    } else {
        var tahun = $("#tahun").val();
        var idskpd = $("#idskpd").val();
        var fileinbox = $('#inboxFile').val();
        var nobku = $('#noBKU').val();

        if (jumbarisspj > 0) {

            var varurl = getbasepath() + "/bku/json/updatespjkoreksi";
            var dataac = [];

            var datajour = {
                tahun: tahun,
                idskpd: idskpd,
                fileinbox: fileinbox,
                nobku: nobku

            }
            dataac = datajour;

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

                //$("#keteranganKegPop").val("");
                //$("#idKegpop").val("");
                //clearrowspj();
                bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
            });
        }
    }

}

function clearrowspj() {
    var i;
    var table = document.getElementById('spjtablebody');
    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function CEK() {
    for (var i = 1; i <= jumbarisspj; i++) {
        console.log("i = " + i + " - penanda = " + $("#penanda" + i).val());
    }
}

function hitungnilaispj() {
    var total = 0;

    var searchIDs = $("#spjtable input:checkbox:checked").map(function() {
        return $(this).val();
    }).get();

    //console.log("searchIDs = "+ searchIDs);
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + data).val(), ","));
    })
    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalspjhidden").val(total);
}

function getNilaiSisaSpj() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var nobku = $('#noBKU').val();
    var beban = $('#beban').val();
    var idkegiatan = $("#idKegiatan").val();

    $.getJSON(getbasepath() + "/bku/json/getNilaiSisaSpj", {tahun: tahun, idskpd: idskpd, nobku: nobku, beban: beban, idkegiatan: idkegiatan},
    function(result) {
        var banyak;

        var nilai = result.aData['nilaiSisa'];
        $('#sisaspj').val(accounting.formatNumber(nilai, 2, '.', ","));
        //console.log("nilai sisa spj = "+result.aData['nilaiSisa']);
    });

}

function setPembayaran(bayar) {
    if (bayar == "3") {
        document.getElementById("labelnippptk").style.display = "block";
        document.getElementById("labelnamapptk").style.display = "block";
    } else {
        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
        $("#namaPptk").val("");
        $("#nipPptk").val("");
    }
}

function getNilaiValidasiSisaSpj() { // dibuat 22 jan 16 by zainab, untuk memvalidasi total bku 

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $("#idKegiatan").val();
    var nobku = $('#noBKU').val();
    var beban = $('#beban').val();

    //console.log("idkegiatan = " + idkegiatan);
    if (idkegiatan !== "") {
        $.getJSON(getbasepath() + "/bku/json/getNilaiValidasiSisaSpj", {tahun: tahun, idskpd: idskpd, nobku: nobku, idkegiatan: idkegiatan, beban: beban},
        function(result) {

            var nilaispd = result.aData['nilaiSpd'];
            var nilaikontrak = result.aData['nilaiKontrak'];
            var nilaibku = result.aData['nilaiBku'];
            var nilaisisa = result.aData['nilaiSisa'];
            var nilaitu = result.aData['nilaiTU'];
            var setortu = result.aData['nilaiSetoranTU'];

            $('#nilaispd').val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $('#nilaikontrak').val(accounting.formatNumber(nilaikontrak, 2, '.', ","));
            $('#nilaibku').val(accounting.formatNumber(nilaibku, 2, '.', ","));
            $('#sisaspd').val(accounting.formatNumber(nilaisisa, 2, '.', ","));
            $('#nilaispptu').val(accounting.formatNumber(nilaitu, 2, '.', ","));
            $('#nilaisetorantu').val(accounting.formatNumber(setortu, 2, '.', ","));

        });
    }

}

function getKodeTutup() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getKodeTutup", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;


        if (banyak > 0) {
            console.log("masuk banyak > 0");
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];

            console.log("tgltutup = " + tgltutup);

            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";

            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";

            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";

            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";

            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";

            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";

            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";

            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";

            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";

            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";

            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";

            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';

            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
            }

        } else { // jika awal tahun, belum ada yang insert data
            opt = '<option value="01" >01 - Januari</option>';
        }

        $("#bulan").html(opt);
    });


}

function getBulanByRekap() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getBulanByRekap", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan;
        var opt;

        
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];

            if (kodebulan == "13") {
                kodebulan = "";
                bulan = "Sudah Tutup Buku Akhir Tahun";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';

            $("#bulan").html(opt);

        } else { // jika awal tahun, belum ada yang insert data
            opt = '<option value="01" >01 - Januari</option>';

            $("#bulan").html(opt);
        }

    });

}
