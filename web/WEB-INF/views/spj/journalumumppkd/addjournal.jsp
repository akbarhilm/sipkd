<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    
    function ambilakun(idrow) {
        var kode = $("#kodeakun"+idrow).val();
        window.localStorage.setItem("kodeakunval",kode);
        
    }
</script>

<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/spj/journalumumppkd/simpanJournal" class="form-horizontal">

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Jurnal Umum PPKD </a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""} >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Jurnal Umum PPKD</div>
        <div class="actions">
        </div>
    </div>
    <div class="portlet-body flip-scroll">
       <form class="form-horizontal"> 
            
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" id="tahunAngg" size="5"  class="m-wrap medium inputnumber" readonly="true" value="${tahunAnggaran}"  onchange="gridspmpotayat()" />
                            <input type="hidden" id="idskpd"  value="${skpd.idSkpd}" />
                        </div>
                    </div>
            </div>  
            
            <div class="form-group">
                <label class="col-md-3 control-label">No Jurnal :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="hidden" id="nojurhidden" name="nojurhidden" value="" />
                            <form:input path="noJournal" type="text"  id="noJournal" value="${noJournal}" class="m-wrap large" readonly="true"/>
                            <a id="nojourpilih"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/journalppkd/listjournal?target='_top'" title="Pilih No Jurnal"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </div>
                    </div>
            </div> 
       
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Jurnal :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input type="text"  path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                            <form:errors path="tglPosting" class="error" />
                        </div>
                    </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">No Berita Acara :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="noDok" id="noDok" type="text" value="" size="50" /> 
                            
                        </div>
                    </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Berita Acara :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input type="text"  path="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                            <form:errors path="tglDok" class="error" />
                        </div>
                    </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="ketJour" id="ketJour" type="text" value="" size="80" /> 
                        <input type="hidden" id="ketupdate" name="ketupdate" value="${ketupdate}"  onchange="getbanyakrinci()">
                        <input type="hidden" id="banyakrinci" name="banyakrinci"  />
                    </div>
                </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">Koreksi dari BPK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="ketkoreksi" name="ketkoreksi" value="${ketkoreksi}"  onchange="setkoreksi(this.value)">
                        <input type="checkbox"  id="koreksidariBPK" name="koreksidariBPK" value="" class="checked" />   
                    </div>
                </div>
            </div>             
    </form>                        

    </div>
</div>
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="journaltable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>SKPD</th>
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Koreksi</th>
                        <th>Debet</th>
                        <th>Kredit</th>
                        <th>Jenis</th>
                        <th>Beban</th>
                        <th>Kegiatan</th>
                        <th>Nama Kegiatan</th>
                    </tr>
                </thead>
                
                <tbody id="jourtablebody" >
                    <tr>
                        <input type="hidden" id="skpdpop" name="skpdpop"  onchange="getidskpd()" value="">
                        <input type="hidden" id="idskpdpop" name="idskpdpop"  onchange="getidskpd()" value="">
                        <input type="hidden" id="idakunpop" name="idakunpop"  onchange="getidakun()" value="">
                        <input type="hidden" id="namaakunpop" name="namaakunpop"  onchange="getidakun()" value="">
                        <input type="hidden" id="idbaspop" name="idbaspop"  onchange="getidakun()" value="">
                        <input type="hidden" id="idkegpop" name="idkegpop"  onchange="getkegiatan()" value="">
                        <input type="hidden" id="kodekegpop" name="kodekegpop"  onchange="getkegiatan()" value="">
                        <input type="hidden" id="namakegpop" name="namakegpop"  onchange="getkegiatan()" value="">
                        
                    </tr>
                </tbody>   
                
                <tfoot id="jourtablefoot" >
                        <tr>
                            <th colspan="5" style="text-align:right">Total:</th>
                            <th colspan="1">
                                <input  type='text' id="totaldebet"  name="totaldebet" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                            </th>
                            <th colspan="1" >
                                <input type='text' id="totalkredit"   name="totalkredit" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                            </th>
                        </tr>
                    </tfoot>
            </table>
        </div>
    </form>
</div>
<div class="form-actions fluid">
     <div class="col-md-offset-3 col-md-9" align="Right">
          <button type="button" class="btn blue" onclick='cektotal()'>Simpan</button>
          <button type="button" class="btn blue" onclick='tambahRow()' >Tambah Data</button>
          <button type="button" class="btn dark-grey" onclick='clearrow()' >Clear</button>
          <a  title="Hapus" onclick="hapusdata()" class='btn red linkpilihan' href="#">Hapus</a>
          <a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a>
          <!-- <button type="button" class="btn blue" onclick='tampilCek()' >Cek</button> -->
     </div>
</div>
     
     </form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journalumumppkd/journalppkd.js"></script>    
