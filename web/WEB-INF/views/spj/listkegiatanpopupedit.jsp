<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<script>
    $(document).ready(function() {
        
        
    });
</script>


<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Pilih  Kegiatan</div>
    </div>

    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="refspj"  id="refspj"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    
            <input type="hidden" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" readOnly="true"/> 
            <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" />
            <input type="hidden"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
            <input type="hidden" id='idspj' name='idspj' value="${idspj}"   />
            <input type="hidden" id="banyakrinci" name="banyakrinci"  />
            <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />
            
            <div class="form-group">
                <label class="col-md-3 control-label">Program:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input size="80" type="text" path="program.namaProgram"   id="namaProgram"  class="m-wrap large" readOnly="true"/> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan</label>
                <div class="col-md-9">
                    <form:input  type="hidden" id="idKegiatan" path="kegiatan.idKegiatan" name="idKegiatan" onchange="gridspjrincikegiatan()" value="${idkegiatan}"   />
                    <form:input type="text"  path="kegiatan.namaKegiatan" name="namaKegiatan"  id="namaKegiatan"  class="m-wrap large" size="80" readOnly="true"/> 
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">Pagu UP </label>
                <div class="col-md-9"> 
                    <form:input  type="text"  path="nilaiPaguUp"  id="nilaiPaguUp"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Total SPP </label> 
                    <form:input  type="text"  path="totalSpp"  id="totalSpp"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Total Entry SPJ </label> 
                    <form:input  type="text"  path="entrySpj"  id="entrySpj"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
               </div>
               
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Sisa Uang UP/GU</label>
                <div class="col-md-9"> 
                    <form:input  type="text"  path="sisaUang"  id="sisaUang"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Persen Sisa Penyerapan  </label> 
                    <form:input  type="text"  path="persentase"  id="persentase"  class="m-wrap large" size="7" readOnly="true" /> %&nbsp 
                    
               </div>
               
            </div>
                    
            <div id="labelsisaTU" class="form-group">
                <label class="col-md-3 control-label">Sisa Uang TU</label>
                <div class="col-md-9"> 
                    <input type="text"  name="sisaUangTU"  id="sisaUangTU"  class="input money" size="19" readOnly="true" style="text-align:right" />
                    
               </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Beban</label>
                <div class="col-md-9"> 
                    <form:hidden  path="kodeBeban"  id="kodeBeban"  class="m-wrap large" size="7" readOnly="true" /> 
                    <input type="text" id="Beban" name="Beban" size="7" readOnly="true" />
               </div>
            </div>
        </form:form>

        </div>
    </div>   
               
    <form id="formspjrincikegiatan">
    <div class="portlet box">
        <div class="portlet-title">
        </div>
        <div class="form-actions fluid">
               <button type="button" class="btn blue" onclick='ubahnilaispj( )'>Ubah</button>
               <label class=" col-md-offset-3 col-md-9" align="Right" >*)Ubah Data Perhalaman</label>
            <!--  <button type="button" class="btn blue" onclick='cek()'>CEK</button> -->
            <!-- <div  style='float: right'><button id="buttoninduk" type="submit" class="btn blue" >Simpan</button></div> -->
        </div>
        <div class="portlet-body">
            
            <table id="editkegiatanTUtable" class="table table-striped table-bordered table-condensed table-hover " style="width: 1000px;" >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS</th>
                        <th>Nilai TU</th>
                        <th>Sudah SPJ</th>
                        <th>Sisa SPJ</th>
                        <th>Nilai SPJ</th>
                        <th></th> 
                    </tr>
                </thead>
                <tfoot>
                     <tr>
                        <th colspan="8" style="text-align:right">Total:</th>
                        <!--
                        <th colspan="2">
                            <input  type='text' id="totalsisaspj"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                        -->
                        <th colspan="1" >
                            <input type='text' id="sumspjtu"   name="sumspjtu" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>
                <tbody>
                </tbody> 
            </table>
            
            <table id="editkegiatanUPtable" class="table table-striped table-bordered table-condensed table-hover " style="width: 1000px;" >
                <thead>
                    <tr>
                        <th >No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS</th>
                        <th>Sudah SPJ</th>
                        <th>Sisa Belum SPJ</th>
                        <th>Nilai SPJ</th>
                        <th></th> 
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th colspan="7" style="text-align:right">Total:</th>
                       <!-- <th colspan="1">
                            <input  type='text' id="totalsisaspj"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                        <th colspan="1" >
                            <input type='text' id="sumspj"   name="sumspj" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>-->
                       
                       <th colspan="1" >
                            <input type='text' id="sumspjup"   name="sumspjup" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>
                <tbody>
                </tbody> 
            </table>
        </div>
    </div>
    </form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spj/editkegiatan.js"></script>  
