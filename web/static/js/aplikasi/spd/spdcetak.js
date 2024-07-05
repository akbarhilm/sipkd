$(document).ready(function() {
    var timer = $.timer(function() {
        var idskpd = $("#idskpd").val();
        if (idskpd && idskpd != 'undefined' && $.trim(idskpd).length > 0) {
            gridspdbtlcetaklist();
        }
    });
    timer.set({time: 120000, autostart: true});
});
function getlistspdcetak(id) {
    $.getJSON(getbasepath() + "/common/json/getpagudansisa/" + id,
            function(result) {
                $('#pagubtl').val(accounting.formatNumber(result.pagu));
                $('#sisaspd').val(accounting.formatNumber(result.vspd));
            });

    gridspdbtlcetaklist( );
}


function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
        var idpejabatppkd = $("#nippejabatppkd" + idspd).val();
        var namapejabatppkd = $("#namapejabatppkd" + idspd).val();
        var njabatanpejabatppkd = $("#njabpejabatppkd" + idspd).val();
        var nrkpejabat = $("#nrkpejabatppkd" + idspd).val();
        var nipPA = $("#nipPA" + idspd).val();
        var namaPA = $("#namaPA" + idspd).val();
        var nrkPA = $("#nrkPA" + idspd).val();
        var nipPK = $("#nipPK" + idspd).val();
        var namaPK = $("#namaPK" + idspd).val();
        var nrkPK = $("#nrkPK" + idspd).val();
        var akun = $("#akun" + idspd).val();
        var jenis = $("#jenis" + idspd).val();
        var nospd = $("#nospd" + idspd).val();
        var p1 = $("#p1" + idspd).val();
        var p2 = $("#p2" + idspd).val();
        var p5 = $("#p5" + idspd).val();
        var p6 = $("#p6" + idspd).val();
        var statusttd = $("#statusttd" + idspd).val();

        if (jenis == 'BTL')
        {
            var jns = '1';
        } else if (jenis == 'BTL BANTUAN')
        {
            var jns = '2';
        } else if (jenis == 'BL')
        {
            var jns = '3';
        } else if (jenis == 'BIAYA')
        {
            var jns = '4';
        }

        // //alert(nipPA); //alert(namaPA); //alert(nrkPA);
        // alert(jenis+nospd);
        // alert(nospd);
        //alert(nrkPK);
        //alert(akun);
        if (idpejabatppkd == null || idpejabatppkd == '' || idpejabatppkd == 'undefined' || $.trim(idpejabatppkd).length < 1) {
            statuscek++;
        } else {
            var map = {"idspd": idspd,
                "nip": idpejabatppkd,
                "namapeg": namapejabatppkd,
                "namajabatan": njabatanpejabatppkd,
                "nrkPegawai": nrkpejabat,
                "nipPA": nipPA,
                "namaPA": namaPA,
                "nrkPA": nrkPA,
                "nipPK": nipPK,
                "namaPK": namaPK,
                "nrkPK": nrkPK,
                "jenis": jns,
                "nospd": nospd,
                "p1": p1,
                "p2": p2,
                "p5": p5,
                "p6": p6,
                "statusttd": statusttd
            };
            selected.push(map);
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/spd/pengajuancetak/json/insertspdcetak",
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(selected)
        }).always(function(data) {
            gridspdbtlcetaklist();

            bootbox.alert(data.responseText);
        });
    } else {

        bootbox.alert("Pejabat penandatangan wajib dipilih sebelum cetak!");
    }
}

function gridspdbtlcetaklist() {
    var urljson = getbasepath() + "/spd/pengajuancetak/json/getlistspdcetak";
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
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "id", value: $('#idskpd').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()}
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
                $('td:eq(0)', nRow).html(index);
                //$('td:eq(1)', nRow).html(aData['NOSPD']);
                //$('td:eq(2)', nRow).html(getTanggal(aData['D_SPDNO']));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['NILAISPD']));
                // $('td:eq(6)', nRow).html(aData['TGLCETAK'] != null && aData['TGLCETAK'] != '' && aData['TGLCETAK'] != 'undefined' ? moment.unix(aData['TGLCETAK'], 'SSS').format('DD-MM-YYYY') : '-');
                var namapeg = (aData['NAMAPEGAWAI'] != null && aData['NAMAPEGAWAI'] != '' && aData['NAMAPEGAWAI'] != 'undefined') ? aData['NAMAPEGAWAI'] : '-';
                var namajab = aData['NAMAJABATAN'] != null && aData['NAMAJABATAN'] != '' && aData['NAMAJABATAN'] != 'undefined' ? aData['NAMAJABATAN'] : '-';
                //var buttonnamattd = aData['STATUS'] == 'PENGAJUAN' ? '<a  onclick="tampilcek(' + aData['IDSPD'] + ')" class="fancybox fancybox.iframe btn dark" href="' + getbasepath() + '/common/listpejabatppkd/' + aData['IDSPD'] + '/SPD/' + aData['NILAISPD'] + '?target=top" title="Pilih PejabatPPKD"  ><i class="icon-zoom-in"></i></a>' : " ";
                var inputhiddenidpejabat = "<input type='hidden'  name='idpejabatppkd" + aData['IDSPD'] + "'   id='idpejabatppkd" + aData['IDSPD'] + "'  />";
                var inputhiddennippejabat = "<input type='hidden'  name='nippejabatppkd" + aData['IDSPD'] + "'   id='nippejabatppkd" + aData['IDSPD'] + "' value='" + aData['NIPPEGAWAI'] + "'  />";
                var inputhiddennamapejabat = "<input type='hidden'  name='namapejabatppkd" + aData['IDSPD'] + "'   id='namapejabatppkd" + aData['IDSPD'] + "' value='" + aData['NAMAPEGAWAI'] + "'  />";
                var inputhiddennjabpejabat = "<input type='hidden'  name='njabpejabatppkd" + aData['IDSPD'] + "'   id='njabpejabatppkd" + aData['IDSPD'] + "' value='" + aData['NAMAJABATAN'] + "'  />";
                var inputhiddenrkpejabat = "<input type='hidden'  name='nrkpejabatppkd" + aData['IDSPD'] + "'   id='nrkpejabatppkd" + aData['IDSPD'] + "' value='" + aData['NRKPEGAWAI'] + "'  />";


                var inputhiddennipPA = "<input type='hidden'    id='nipPA" + aData['IDSPD'] + "' value='" + aData['I_NIP_PA'] + "'  />";
                var inputhiddennamaPA = "<input type='hidden'    id='namaPA" + aData['IDSPD'] + "' value='" + aData['N_PA'] + "'  />";
                var inputhiddenrkPA = "<input type='hidden'      id='nrkPA" + aData['IDSPD'] + "' value='" + aData['I_NRK_PA'] + "'  />";

                //    var inputhiddennipPK = "<input type='hidden'   id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBLJ'] + "'  />";
                //     var inputhiddennamaPK = "<input type='hidden'     id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBLJ'] + "'  />";
                //     var inputhiddenrkPK = "<input type='hidden'    id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBLJ'] + "'  />";

                //alert(inputhiddennipPK);
                //alert(inputhiddennamaPK);
                //alert(inputhiddenrkPK);

                var level = $('#levelSkpd').val();
                var jen = aData['JENIS'];
                var aku = aData['C_AKUN'];




                var akun = "<input type='hidden'  name='akun" + aData['IDSPD'] + "'   id='akun" + aData['IDSPD'] + "' value='" + aData['C_AKUN'] + "'  />";
                var jenis = "<input type='hidden'  name='jenis" + aData['IDSPD'] + "'   id='jenis" + aData['IDSPD'] + "' value='" + aData['JENIS'] + "'  />";
                var nospd = "<input type='hidden'  name='nospd" + aData['IDSPD'] + "'   id='nospd" + aData['IDSPD'] + "' value='" + aData['NOSPD'] + "'  />";

                var p1 = "<input type='hidden'  name='p1" + aData['IDSPD'] + "'   id='p1" + aData['IDSPD'] + "' value='" + aData['E_PERATURAN_SPD1'] + "'  />";
                var p2 = "<input type='hidden'  name='p2" + aData['IDSPD'] + "'   id='p2" + aData['IDSPD'] + "' value='" + aData['E_PERATURAN_SPD2'] + "'  />";
                var p5 = "<input type='hidden'  name='p5" + aData['IDSPD'] + "'   id='p5" + aData['IDSPD'] + "' value='" + aData['E_PERATURAN_SPD5'] + "'  />";
                var p6 = "<input type='hidden'  name='p6" + aData['IDSPD'] + "'   id='p6" + aData['IDSPD'] + "' value='" + aData['E_PERATURAN_SPD6'] + "'  />";

                var nipbktt = "<input type='hidden'  name='nipbktt" + aData['IDSPD'] + "'   id='nipbktt" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBTT'] + "'  />";
                var nbktt = "<input type='hidden'  name='nbktt" + aData['IDSPD'] + "'   id='nbktt" + aData['IDSPD'] + "' value='" + aData['I_N_PKBTT'] + "'  />";
                var nkbktt = "<input type='hidden'  name='nkbktt" + aData['IDSPD'] + "'   id='nkbktt" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBTT'] + "'  />";

                var nipbklj = "<input type='hidden'  name='nipbklj" + aData['IDSPD'] + "'   id='nipbklj" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBLJ'] + "'  />";
                var nbklj = "<input type='hidden'  name='nbklj" + aData['IDSPD'] + "'   id='nbklj" + aData['IDSPD'] + "' value='" + aData['I_N_PKBLJ'] + "'  />";
                var nkbklj = "<input type='hidden'  name='nkbklj" + aData['IDSPD'] + "'   id='nkbklj" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBLJ'] + "'  />";

                var nipbiaya = "<input type='hidden'  name='nipbiaya" + aData['IDSPD'] + "'   id='nipbiaya" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBIAYA'] + "'  />";
                var nbiaya = "<input type='hidden'  name='nbiaya" + aData['IDSPD'] + "'   id='nbiaya" + aData['IDSPD'] + "' value='" + aData['I_N_PKBIAYA'] + "'  />";
                var nkbiaya = "<input type='hidden'  name='nkbiaya" + aData['IDSPD'] + "'   id='nkbiaya" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBIAYA'] + "'  />";

                var nipbantuan = "<input type='hidden'  name='nipbantuan" + aData['IDSPD'] + "'   id='nipbantuan" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBANTUAN'] + "'  />";
                var nbantuan = "<input type='hidden'  name='nbantuan" + aData['IDSPD'] + "'   id='nbantuan" + aData['IDSPD'] + "' value='" + aData['I_N_PKBANTUAN'] + "'  />";
                var nkbantuan = "<input type='hidden'  name='nkbantuan" + aData['IDSPD'] + "'   id='nkbantuan" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBANTUAN'] + "'  />";



                if (level == '3')
                {
                    if (jen == 'BTL')
                    {
                        if (aku == '5.1.8')
                        {
                            console.log(level + " = " + jen + " = " + aku)
                            var inputhiddennipPK = "<input type='hidden'    id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBTT'] + "'  />";
                            var inputhiddennamaPK = "<input type='hidden'    id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBTT'] + "'  />";
                            var inputhiddenrkPK = "<input type='hidden'      id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBTT'] + "'  />";
                        } else
                        {


                            var inputhiddennipPK = "<input type='hidden'    id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBLJ'] + "'  />";
                            var inputhiddennamaPK = "<input type='hidden'    id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBLJ'] + "'  />";
                            var inputhiddenrkPK = "<input type='hidden'      id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBLJ'] + "'  />";
                        }
                    } else if (jen == 'BIAYA')
                    {

                        var inputhiddennipPK = "<input type='hidden'    id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBIAYA'] + "'  />";
                        var inputhiddennamaPK = "<input type='hidden'    id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBIAYA'] + "'  />";
                        var inputhiddenrkPK = "<input type='hidden'      id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBIAYA'] + "'  />";
                    } else if (jen == 'BTL BANTUAN')
                    {

                        var inputhiddennipPK = "<input type='hidden'    id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBANTUAN'] + "'  />";
                        var inputhiddennamaPK = "<input type='hidden'    id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBANTUAN'] + "'  />";
                        var inputhiddenrkPK = "<input type='hidden'      id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBANTUAN'] + "'  />";

                    } else if (jen == 'BL')
                    {

                        var inputhiddennipPK = "<input type='hidden'    id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBLJ'] + "'  />";
                        var inputhiddennamaPK = "<input type='hidden'    id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBLJ'] + "'  />";
                        var inputhiddenrkPK = "<input type='hidden'      id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBLJ'] + "'  />";
                    }



                } else
                {

                    var inputhiddennipPK = "<input type='hidden'    id='nipPK" + aData['IDSPD'] + "' value='" + aData['I_NIP_PKBLJ'] + "'  />";
                    var inputhiddennamaPK = "<input type='hidden'    id='namaPK" + aData['IDSPD'] + "' value='" + aData['I_N_PKBLJ'] + "'  />";
                    var inputhiddenrkPK = "<input type='hidden'      id='nrkPK" + aData['IDSPD'] + "' value='" + aData['I_NRK_PKBLJ'] + "'  />";
                    // console.log(level+" = "+jen+" = "+aku);
                    // console.log(inputhiddennamaPA);
                }





                //var namafile = "SPD" + aData['JENIS'] + "." + aData['C_ANGG_TAHUN'] + "." + aData['NOSPD'] + ".pdf"
                var spannamapejabat = "<span id='identitaspejabat" + aData['IDSPD'] + +"' >" + aData['NAMAPEGAWAI'] + "/" + aData['NAMAJABATAN'] + "</span>";
                var cekprint = "<input type='checkbox' name='cek" + aData['IDSPD'] + "'  id='cek" + aData['IDSPD'] + "' value='" + aData['IDSPD'] + "' class='checkbox' />";
                var stat = aData['STATUSCETAK'];
                //alert(stat);
                var donlod = "-";
                var batal = "-";
                var namafile = "-";
                if (jen == 'BTL')
                {
                    namafile = "SPD" + aData['JENIS'] + "-" + aData['C_ANGG_TAHUN'] + "-" + aData['NOSPD'] + ".pdf"
                } else if (jen == 'BTL BANTUAN')
                {
                    namafile = "SPD" + aData['JENIS'] + "-" + aData['C_ANGG_TAHUN'] + "-" + aData['NOSPD'] + ".pdf"
                } else if (jen == 'BL')
                {
                    namafile = "SPD" + aData['JENIS'] + "-" + aData['C_ANGG_TAHUN'] + "-" + aData['NOSPD'] + ".pdf"
                } else if (jen == 'BIAYA')
                {
                    namafile = "SPD" + aData['JENIS'] + "-" + aData['C_ANGG_TAHUN'] + "-" + aData['NOSPD'] + ".pdf"
                }


                if (aData['STATUS'] == 'PENGAJUAN') {
                    $('td:eq(7)', nRow).html(aData['NAMAPEGAWAI'] + "/" + aData['NAMAJABATAN'] + inputhiddenidpejabat + inputhiddennippejabat + inputhiddennamapejabat + inputhiddennjabpejabat + inputhiddenrkpejabat + inputhiddennipPA + inputhiddennamaPA + inputhiddenrkPA + inputhiddennipPK + inputhiddennamaPK + inputhiddenrkPK + akun + jenis + nospd + p1 + p2 + p5 + p6);

                } else if (aData['STATUS'] == 'CETAK') {
                    $('td:eq(7)', nRow).html(namapeg + "/" + namajab);
                    cekprint = "-";

                    if (aData['STATUSCETAK'] == '2' || aData['STATUSCETAK'] == '1') {

                        donlod = "<a class='icon-book' href='" + getbasepath() + "/spd/pengajuancetak/" + aData['JENIS'] + "/" + aData['IDSPD'] + "/" + aData['NOSPD'] + "/" + namafile + "' ></a>";
                        batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['IDSPD'] + ",'" + aData['NOSPD'] + "') ></a>";
                    } else if (aData['STATUSCETAK'] == '3') {

                        cekprint = "-";
                        donlod = "Prosessing...";
                        batal = "-";
                    }
                }

                // tambahan 140119 oleh zainab -- tambah status penandatangan
                var statusttd = "<select id='statusttd" + aData['IDSPD'] + "' name='statusttd" + aData['IDSPD'] + "'><option value='0'>Definitif</option><option value='1'>PLT</option><option value='2'>PLH</option></select>";
                if (cekprint == "-") { // jika sudah proses
                    $('td:eq(8)', nRow).html(cekprint);
                } else {
                    $('td:eq(8)', nRow).html(statusttd);
                }

                $('td:eq(9)', nRow).html(cekprint);
                $('td:eq(10)', nRow).html(donlod);
                $('td:eq(11)', nRow).html(batal);
                return nRow;
            },
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "aoColumns": [
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "center"},
                {"mDataProp": "NOSPD", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "JENIS", "bSortable": false, sClass: "left"},
                {"mDataProp": "D_SPDNO", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "NILAISPD", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "STATUS", "bSortable": false, sClass: "left"},
                {"mDataProp": "TGLCETAK", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "NAMAPEGAWAI", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "false", sClass: "center"},
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "false", sClass: "center"},
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "false", sClass: "center"},
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "false", sClass: "center"}
            ]
        });
        $("div.top").html("<button onclick=trigercetak() class='btn .dark-stripe' style='float: right'>Proses Cetak</button>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function pindahhalcetakakun() {

    $("#popupcetakakun").prop("href", getbasepath() + "/spd/pengajuancetak/reports/pdf/");
    $("#popupcetakakun").click();


}

function tampilcek(id) {
    $("#cek" + id).show();
}

function batalspd(idspd, nospd) {
    var urlhapusspd = getbasepath() + "/spd/pengajuancetak/json/hapusspdcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SPD dengan nomor " + nospd + " di " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospd": nospd})
            }).always(function(data) {
                gridspdbtlcetaklist();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}


