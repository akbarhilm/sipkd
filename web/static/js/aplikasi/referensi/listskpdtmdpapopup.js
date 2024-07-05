$(document).ready(function() {
   grid(); 
});

 

function ambilskpd(id) {
    $("#bendaharalist", window.parent.document).remove();
    $('#idTmdpa', window.parent.document).val($("#idTmdpa" + id).val());
    $('#idPendapatan', window.parent.document).val($("#idPendapatan" + id).val());
    $('#skpdLevel', window.parent.document).val($("#skpdLevel" + id).val());
    $('#skpd', window.parent.document).val($("#namaSkpd" + id).val());
    $('#idskpd', window.parent.document).val(id).change();
    
    $('#paguDpt', window.parent.document).val(accounting.formatNumber($("#paguDpt" + id).val()));
    $('#paguBtl', window.parent.document).val(accounting.formatNumber($("#paguBtl" + id).val()));
    $('#paguBl', window.parent.document).val(accounting.formatNumber($("#paguBl" + id).val()));
    $('#paguBiaya', window.parent.document).val(accounting.formatNumber($("#paguBiaya" + id).val()));
    /*
    $('#paguDpt', window.parent.document).val($("#paguDpt" + id).val());
    $('#paguBtl', window.parent.document).val($("#paguBtl" + id).val());
    $('#paguBl', window.parent.document).val($("#paguBl" + id).val());
    $('#paguBiaya', window.parent.document).val($("#paguBiaya" + id).val());
    */
    $('#namaPa', window.parent.document).val($("#namaPa" + id).val());
    $('#nipPa', window.parent.document).val($("#nipPa" + id).val());
    $('#nrkPa', window.parent.document).val($("#nrkPa" + id).val());
    
    if($("#noDpa" + id).val() != 'null'){
        $('#noDpa', window.parent.document).val($("#noDpa" + id).val());
    }else{
        $('#noDpa', window.parent.document).val("");
    }
    if($("#tglDpa" + id).val() != 'null'){
        $('#tglDpa', window.parent.document).val($("#tglDpa" + id).val());
    }else{
        $('#tglDpa', window.parent.document).val("");
    }
    if($("#noDpaPrbh" + id).val() != 'null'){
        $('#noDpaPrbh', window.parent.document).val($("#noDpaPrbh" + id).val());
    }else{
        $('#noDpaPrbh', window.parent.document).val("");
    }
    if($("#tglDpaPrbh" + id).val() != 'null'){
        $('#tglDpaPrbh', window.parent.document).val($("#tglDpaPrbh" + id).val());
    }else{
        $('#tglDpaPrbh', window.parent.document).val("");
    }
    
    if($("#nrkBenPn" + id).val() != 'null'){
        $('#nrkBenPn', window.parent.document).val($("#nrkBenPn" + id).val());
    }else{
        $('#nrkBenPn', window.parent.document).val("");
    }
    if($("#nipBenPn" + id).val() != 'null'){
        $('#nipBenPn', window.parent.document).val($("#nipBenPn" + id).val());
    }else{
        $('#nipBenPn', window.parent.document).val("");
    }
    if($("#namaBenPn" + id).val() != 'null'){
        $('#namaBenPn', window.parent.document).val($("#namaBenPn" + id).val());
    }else{
        $('#namaBenPn', window.parent.document).val("");
    }
   
    if($("#nrkBenPg" + id).val() != 'null'){
        $('#nrkBenPg', window.parent.document).val($("#nrkBenPg" + id).val());
    }else{
        $('#nrkBenPg', window.parent.document).val("");
    }
    if($("#nipBenPg" + id).val() != 'null'){
        $('#nipBenPg', window.parent.document).val($("#nipBenPg" + id).val());
    }else{
        $('#nipBenPg', window.parent.document).val("");
    }
    if($("#namaBenPg" + id).val() != 'null'){
        $('#namaBenPg', window.parent.document).val($("#namaBenPg" + id).val());
    }else{
        $('#namaBenPg', window.parent.document).val("");
    }
    
    
    if($("#nrkBenPgBantuan" + id).val() != 'null'){
        $('#nrkBenPgBantuan', window.parent.document).val($("#nrkBenPgBantuan" + id).val());
    }else{
        $('#nrkBenPgBantuan', window.parent.document).val("");
    }
    if($("#nipBenPgBantuan" + id).val() != 'null'){
        $('#nipBenPgBantuan', window.parent.document).val($("#nipBenPgBantuan" + id).val());
    }else{
        $('#nipBenPgBantuan', window.parent.document).val("");
    }
    if($("#namaBenPgBantuan" + id).val() != 'null'){
        $('#namaBenPgBantuan', window.parent.document).val($("#namaBenPgBantuan" + id).val());
    }else{
        $('#namaBenPgBantuan', window.parent.document).val("");
    }
    
   
    
    if($("#nrkBenPgBtt" + id).val() != 'null'){
        $('#nrkBenPgBtt', window.parent.document).val($("#nrkBenPgBTT" + id).val());
    }else{
        $('#nrkBenPgBtt', window.parent.document).val("");
    }
    if($("#nipBenPgBtt" + id).val() != 'null'){
        $('#nipBenPgBtt', window.parent.document).val($("#nipBenPgBTT" + id).val());
    }else{
        $('#nipBenPgBtt', window.parent.document).val("");
    }
    if($("#namaBenPgBtt" + id).val() != 'null'){
        $('#namaBenPgBtt', window.parent.document).val($("#namaBenPgBTT" + id).val());
    }else{
        $('#namaBenPgBtt', window.parent.document).val("");
    }
   
    
    if($("#nrkBenPgPembiayaan" + id).val() != 'null'){
        $('#nrkBenPgPembiayaan', window.parent.document).val($("#nrkBenPgPembiayaan" + id).val());
    }else{
        $('#nrkBenPgPembiayaan', window.parent.document).val("");
    }
    if($("#nipBenPgPembiayaan" + id).val() != 'null'){
        $('#nipBenPgPembiayaan', window.parent.document).val($("#nipBenPgPembiayaan" + id).val());
    }else{
        $('#nipBenPgPembiayaan', window.parent.document).val("");
    }
    if($("#namaBenPgPembiayaan" + id).val() != 'null'){
        $('#namaBenPgPembiayaan', window.parent.document).val($("#namaBenPgPembiayaan" + id).val());
    }else{
        $('#namaBenPgPembiayaan', window.parent.document).val("");
    }
    

    if($("#nrkVerifikatorPn" + id).val() != 'null'){
        $('#nrkVerifikatorPn', window.parent.document).val($("#nrkVerifikatorPn" + id).val());
    }else{
        $('#nrkVerifikatorPn', window.parent.document).val("");
    }
    if($("#nipVerifikatorPn" + id).val() != 'null'){
        $('#nipVerifikatorPn', window.parent.document).val($("#nipVerifikatorPn" + id).val());
    }else{
        $('#nipVerifikatorPn', window.parent.document).val("");
    }
    if($("#namaVerifikatorPn" + id).val() != 'null'){
        $('#namaVerifikatorPn', window.parent.document).val($("#namaVerifikatorPn" + id).val());
    }else{
        $('#namaVerifikatorPn', window.parent.document).val("");
    }
    
    
    if($("#nrkVerifikatorPg" + id).val() != 'null'){
        $('#nrkVerifikatorPg', window.parent.document).val($("#nrkVerifikatorPg" + id).val());
    }else{
        $('#nrkVerifikatorPg', window.parent.document).val("");
    }
    if($("#nipVerifikatorPg" + id).val() != 'null'){
        $('#nipVerifikatorPg', window.parent.document).val($("#nipVerifikatorPg" + id).val());
    }else{
        $('#nipVerifikatorPg', window.parent.document).val("");
    }
    if($("#namaVerifikatorPg" + id).val() != 'null'){
        $('#namaVerifikatorPg', window.parent.document).val($("#namaVerifikatorPg" + id).val());
    }else{
        $('#namaVerifikatorPg', window.parent.document).val("");
    }
    
    //alert($("#nipVerifikatorPg" + id).val());
    
    var cPen = $("#idPendapatan" + id).val();
    var cSkpdLevel = $("#skpdLevel" + id).val();
    // Bila C_PENDAPATAN == 1
    if(cPen == 1 ){
        //addformcpn1();
    }
    
    if (cSkpdLevel == 3){
        //addformclevel3();
    }
    
    parent.$.fancybox.close();
}

function addformclevel3(){
    $("<div id='bendaharalist'></div>").appendTo($('#bendaharaholder', window.parent.document));
        // Bendahara Pengeluaran Belanja Bantuan
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>NRK / NIP PKBANTUAN: </label><div class='col-md-4'><div class='input-group'><input type='text' id='nrkbenpgbantuan' size='8'> / <input type='text' id='nipbenpgbantuan' size='20'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>Nama PKBANTUAN: </label><div class='col-md-4'><div class='input-group'><input type='text' id='namabenpgbantuan' size='35'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
        // Bendahara Pengeluaran Belanja Tidak Terduga
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>NRK / NIP PKBTT: </label><div class='col-md-4'><div class='input-group'><input type='text' id='nrkbenpgbtt' size='8'> / <input type='text' id='nipbenpgbtt' size='20'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>Nama PKBTT: </label><div class='col-md-4'><div class='input-group'><input type='text' id='namabenpgbtt' size='35'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
        // Bendahara Pengeluaran Pembiayaan Pengeluaran
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>NRK / NIP PKBIAYA: </label><div class='col-md-4'><div class='input-group'><input type='text' id='nrkbenpgpembiayaan' size='8'> / <input type='text' id='nipbenpgpembiayaan' size='20'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>Nama PKBIAYA: </label><div class='col-md-4'><div class='input-group'><input type='text' id='namabenpgpembiayaan' size='35'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
   
    
}

function addformcpn1(){
    // Bendahara Penerimaan
        $("<div id='bendaharalist'></div>").appendTo($('#bendaharaholder', window.parent.document));
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>NRK / NIP PKDPT: </label><div class='col-md-4'><div class='input-group'><input type='text' id='nrkbenpen' size='8'> / <input type='text' id='nipbenpen' size='20'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
        $("<div class='form-group' id='inpben'><label class='col-md-3 control-label'>Nama PKDPT: </label><div class='col-md-4'><div class='input-group'><input type='text' id='namabenpen' size='35'></div></div></div>").appendTo($('#bendaharalist', window.parent.document));
    
    
}



function grid( ) {
    var urljson = getbasepath()+"/refftmdpa/json/listskpdjson";
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
                        {name: "skpd", value: $('#skpd').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['namaSkpd'] + "' />";
                var idtmdpa = "<input type='hidden' id='idTmdpa" + aData['idSkpd'] + "' value='" + aData['idTmdpa'] + "' />";
                var paguDpt = "<input type='hidden' id='paguDpt" + aData['idSkpd'] + "' value='" + aData['paguDpt'] + "' />";
                var paguBtl = "<input type='hidden' id='paguBtl" + aData['idSkpd'] + "' value='" + aData['paguBtl'] + "' />";
                var paguBl = "<input type='hidden' id='paguBl" + aData['idSkpd'] + "' value='" + aData['paguBl'] + "' />";
                var paguBiaya = "<input type='hidden' id='paguBiaya" + aData['idSkpd'] + "' value='" + aData['paguBiaya'] + "' />";
                var skpdLevel = "<input type='hidden' id='skpdLevel" + aData['idSkpd'] + "' value='" + aData['skpdLevel'] + "' />";
                var idPendapatan = "<input type='hidden' id='idPendapatan" + aData['idSkpd'] + "' value='" + aData['idPendapatan'] + "' />";
                var noDpa = "<input type='hidden' id='noDpa" + aData['idSkpd'] + "' value='" + aData['noDpa'] + "' />";
                var tglDpa = "<input type='hidden' id='tglDpa" + aData['idSkpd'] + "' value='" + aData['tglDpa'] + "' />";
                var noDpaPrbh = "<input type='hidden' id='noDpaPrbh" + aData['idSkpd'] + "' value='" + aData['noDpaPrbh'] + "' />";
                var tglDpaPrbh = "<input type='hidden' id='tglDpaPrbh" + aData['idSkpd'] + "' value='" + aData['tglDpaPrbh'] + "' />";
                var namaPa = "<input type='hidden' id='namaPa" + aData['idSkpd'] + "' value='" + aData['namaPA'] + "' />";
                var nrkPa = "<input type='hidden' id='nrkPa" + aData['idSkpd'] + "' value='" + aData['nrkPA'] + "' />";
                var nipPa = "<input type='hidden' id='nipPa" + aData['idSkpd'] + "' value='" + aData['nipPA'] + "' />";
                
                var nrkBenPn = "<input type='hidden' id='nrkBenPn" + aData['idSkpd'] + "' value='" + aData['nrkBenPn'] + "' />";
                var nipBenPn = "<input type='hidden' id='nipBenPn" + aData['idSkpd'] + "' value='" + aData['nipBenPn'] + "' />";
                var namaBenPn = "<input type='hidden' id='namaBenPn" + aData['idSkpd'] + "' value='" + aData['namaBenPn'] + "' />";
                var nrkBenPg = "<input type='hidden' id='nrkBenPg" + aData['idSkpd'] + "' value='" + aData['nrkBenPg'] + "' />";
                var nipBenPg = "<input type='hidden' id='nipBenPg" + aData['idSkpd'] + "' value='" + aData['nipBenPg'] + "' />";
                var namaBenPg = "<input type='hidden' id='namaBenPg" + aData['idSkpd'] + "' value='" + aData['namaBenPg'] + "' />";
                var nrkBenPgBantuan = "<input type='hidden' id='nrkBenPgBantuan" + aData['idSkpd'] + "' value='" + aData['nrkBenPgBantuan'] + "' />";
                var nipBenPgBantuan = "<input type='hidden' id='nipBenPgBantuan" + aData['idSkpd'] + "' value='" + aData['nipBenPgBantuan'] + "' />";
                var namaBenPgBantuan = "<input type='hidden' id='namaBenPgBantuan" + aData['idSkpd'] + "' value='" + aData['namaBenPgBantuan'] + "' />";
                var nrkBenPgBTT = "<input type='hidden' id='nrkBenPgBTT" + aData['idSkpd'] + "' value='" + aData['nrkBenPgBTT'] + "' />";
                var nipBenPgBTT = "<input type='hidden' id='nipBenPgBTT" + aData['idSkpd'] + "' value='" + aData['nipBenPgBTT'] + "' />";
                var namaBenPgBTT = "<input type='hidden' id='namaBenPgBTT" + aData['idSkpd'] + "' value='" + aData['namaBenPgBTT'] + "' />";
                var nrkBenPgPembiayaan = "<input type='hidden' id='nrkBenPgPembiayaan" + aData['idSkpd'] + "' value='" + aData['nrkBenPgPembiayaan'] + "' />";
                var nipBenPgPembiayaan = "<input type='hidden' id='nipBenPgPembiayaan" + aData['idSkpd'] + "' value='" + aData['nipBenPgPembiayaan'] + "' />";
                var namaBenPgPembiayaan = "<input type='hidden' id='namaBenPgPembiayaan" + aData['idSkpd'] + "' value='" + aData['namaBenPgPembiayaan'] + "' />";
                var nrkVerifikatorPn = "<input type='hidden' id='nrkVerifikatorPn" + aData['idSkpd'] + "' value='" + aData['nrkVerifikatorPn'] + "' />";
                var nipVerifikatorPn = "<input type='hidden' id='nipVerifikatorPn" + aData['idSkpd'] + "' value='" + aData['nipVerifikatorPn'] + "' />";
                var namaVerifikatorPn = "<input type='hidden' id='namaVerifikatorPn" + aData['idSkpd'] + "' value='" + aData['namaVerifikatorPn'] + "' />";
                var nrkVerifikatorPg = "<input type='hidden' id='nrkVerifikatorPg" + aData['idSkpd'] + "' value='" + aData['nrkVerifikatorPg'] + "' />";
                var nipVerifikatorPg = "<input type='hidden' id='nipVerifikatorPg" + aData['idSkpd'] + "' value='" + aData['nipVerifikatorPg'] + "' />";
                var namaVerifikatorPg = "<input type='hidden' id='namaVerifikatorPg" + aData['idSkpd'] + "' value='" + aData['namaVerifikatorPg'] + "' />";
                
                
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnamaskpd + namaPa + nrkPa + nipPa + idtmdpa + paguDpt + paguBtl + paguBl + paguBiaya + idPendapatan + skpdLevel + noDpa + tglDpa + noDpaPrbh + tglDpaPrbh + nrkBenPn + nipBenPn + namaBenPn + nrkBenPg + nipBenPg + namaBenPg + nrkBenPgBantuan + nipBenPgBantuan + namaBenPgBantuan + nrkBenPgBTT +  nipBenPgBTT +  namaBenPgBTT +  nrkBenPgPembiayaan +  nipBenPgPembiayaan +  namaBenPgPembiayaan + nrkVerifikatorPn + nipVerifikatorPn + namaVerifikatorPn + nrkVerifikatorPg + nipVerifikatorPg + namaVerifikatorPg + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSkpd'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


