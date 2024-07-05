$(document).ready(function() {
    grid();
});

var namarekanlist = new Array();

function ambilbast(id) {

    $('#namaKegiatan', window.parent.document).val($("#kodeKegiatan" + id).val() + "/" + $("#namaKegiatan" + id).val());
    $('#nilaiKontrak', window.parent.document).val($("#nilaiKontrak" + id).val());
    //console.log(" nilai kontrak "+$("#nilaiKontrak"+ id).val());
    $('#noKontrak', window.parent.document).val($("#noKontrak" + id).val());
    $('#idKontrak', window.parent.document).val($("#idKontrak" + id).val());
    $('#noBast', window.parent.document).val($("#noBast" + id).val());
    $('#nomorBast', window.parent.document).val($("#noBast" + id).val());
    $('#idBast', window.parent.document).val($("#idBast" + id).val());
    $('#idAkun', window.parent.document).val($("#idAkun" + id).val());


    $('#namaRekanan', window.parent.document).val(namarekanlist[id]);
    //$('#namaRekanan', window.parent.document).val($("#namaRekanan" + id).val());
    $('#dirRekanan', window.parent.document).val($("#direktur" + id).val());
    $('#alamatRekanan', window.parent.document).val($("#alamat" + id).val());
    $('#noNpwp', window.parent.document).val($("#npwp" + id).val());
    $('#namaBank', window.parent.document).val($("#namaBank" + id).val());
    $('#kodeBank', window.parent.document).val($("#kodeBank" + id).val());
    $('#kodeBankTransfer', window.parent.document).val($("#kodeBankTf" + id).val());
    $('#idBank', window.parent.document).val($("#idbank" + id).val());
    $('#rekeningBank', window.parent.document).val($("#rekBank" + id).val());

    $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val()).change();

    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath() + "/bl/json/listbastpopup2";
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
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
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()}
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

                var alamat = aData['alamatRekanan'];
                var namadir = aData['dirRekanan'];

                if (alamat == null) {
                    alamat = "";
                }

                if (namadir == null) {
                    namadir = "";
                }

                console.log("aData namaRekanan = " + aData['namaRekanan']);
                
                var idKegiatan = "<input type='hidden' id='idKegiatan" + aData['bast']['noBast'] + "' value='" + aData['kegiatan']['idKegiatan'] + "' />";
                var kodeKegiatan = "<input type='hidden' id='kodeKegiatan" + aData['bast']['noBast'] + "' value='" + aData['kegiatan']['kodeKegiatan'] + "' />";
                var namaKegiatan = "<input type='hidden' id='namaKegiatan" + aData['bast']['noBast'] + "' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var namaRekanan = "<input type='hidden' id='namaRekanan" + aData['bast']['noBast'] + "' />";
                var noKontrak = "<input type='hidden' id='noKontrak" + aData['bast']['noBast'] + "' value='" + aData['kontrak']['noKontrak'] + "' />";
                var idKontrak = "<input type='hidden' id='idKontrak" + aData['bast']['noBast'] + "' value='" + aData['kontrak']['idKontrak'] + "' />";
                var nilaiKontrak = "<input type='hidden' id='nilaiKontrak" + aData['bast']['noBast'] + "' value='" + aData['kontrak']['nilaiKontrak'] + "' />";
                var noBast = "<input type='hidden' id='noBast" + aData['bast']['noBast'] + "' value='" + aData['bast']['noBast'] + "' />";
                var idBast = "<input type='hidden' id='idBast" + aData['bast']['noBast'] + "' value='" + aData['bast']['idBast'] + "' />";
                //  var idAkun =  "<input type='hidden' id='idAkun" + aData['kegiatan']['idKegiatan']+ "' value='" +  aData['akun']['idAkun'] + "' />";
                var ceklis = "<span class='icon-ok-sign linkpilihan></span>";
                var namakegiatan = " <textarea style='border: none;width: 99%'>" + aData['kegiatan']['namaKegiatan'] + "</textarea>"

                var namadirektur = "<input type='hidden' id='direktur" + aData['bast']['noBast'] + "' value='" + namadir + "' />";
                var alamatperusahaan = "<input type='hidden' id='alamat" + aData['bast']['noBast'] + "' value='" + alamat + "' />";
                var npwp = "<input type='hidden' id='npwp" + aData['bast']['noBast'] + "' value='" + aData['noNpwp'] + "' />";
                var namabank = "<input type='hidden' id='namaBank" + aData['bast']['noBast'] + "' value='" + aData['namaBank'] + "' />";
                var kodebank = "<input type='hidden' id='kodeBank" + aData['bast']['noBast'] + "' value='" + aData['kodeBank'] + "' />";
                var kodebanktf = "<input type='hidden' id='kodeBankTf" + aData['bast']['noBast'] + "' value='" + aData['kodeBankTransfer'] + "' />";
                var rekbank = "<input type='hidden' id='rekBank" + aData['bast']['noBast'] + "' value='" + aData['rekeningBank'] + "' />";
                var idbank = "<input type='hidden' id='idbank" + aData['bast']['noBast'] + "' value='" + aData['idBank'] + "' />";
                
                namarekanlist[aData['bast']['noBast']] = aData['namaRekanan'];  // JIKA ADA TANDA ' KARAKTERNYA TIDAK TERPOTONG
                
                $('td:eq(2)', nRow).html(namakegiatan + kodebanktf + idbank);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(6)', nRow).html(namadirektur + alamatperusahaan + npwp + namabank + kodebank + rekbank + idKegiatan + kodeKegiatan + namaKegiatan + namaRekanan + noKontrak + noBast + nilaiKontrak + idBast + idKontrak + "<span class='icon-ok-sign linkpilihan' onclick='ambilbast(" + aData['bast']['noBast'] + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "bast.noBast", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "kontrak.noKontrak", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "rekanan.namaRekanan", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sDefaultContent: "-", sClass: "center"}

            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}