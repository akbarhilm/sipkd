<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listkegiatanpopup.js"></script>    
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Pilih Kegiatan/Program</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <form:form  method="post" commandName="spjpilihkegiatan"  id="spjpilihkegiatan"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
                    <form:hidden path="idSpj" id='idspj'    />
                    <label class="col-md-3 control-label">Nama Kegiatan:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input type="text"  name="namakegiatan"  id="namakegiatan"   title="klik enter atau tekan tombol cari untuk melakukan pencarian"  class="form-control " size="30" 
                                   onkeyup="if (event.keyCode == 13)
                                               grid( )" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Program:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input type="text"  name="namaprogram"  id="namaprogram"   title="klik enter atau tekan tombol cari untuk melakukan pencarian"  class="form-control " size="30" 
                                   onkeyup="if (event.keyCode == 13)
                                               grid( )" />
                        </div>
                    </div>
                </div>
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button type="button" class="btn dark" onclick='grid( )()'>Cari</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pilih Kegiatan</div>
            <input type="hidden"  name="namaprogram"  id="namaprogram"   title="klik enter atau tekan tombol cari untuk melakukan pencarian"  class="form-control " size="30" 
                   onkeyup="if (event.keyCode == 13)
                                               grid( )" />
            <input type="hidden" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" readOnly="true"/> 
            <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" />
            <input type="hidden"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
            <input type="hidden"  name="idspj"  id="idspj"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
        </div>
    </div>
    <div class="portlet box">
        <div >
            <table id="skpdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>Kode Keg</th>
                        <th>Nama Kegiatan</th>
                        <th>Nama Program</th>
                        <th>Beban</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
                </form:form>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spj/pilihkegiatan.js"></script>  