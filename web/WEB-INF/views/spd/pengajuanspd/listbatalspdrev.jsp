<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/listbatalspd.js"></script>    
<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/spd/pembatalanspdrev/simpanbatalrev" class="form-horizontal">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Pembatalan SPD APBDP</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="namaSkpd" type="text"  name="namaSkpd"  id="namaSkpd" readonly="true" class="m-wrap large" size="90"  value="${namaSkpd}"  />
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-3 control-label">No SPD :</label>
                <div class="col-md-5">
                    <div class="input-group">                      
                        <form:input path="noDokSpd" type="text"  id="noDokSpd" readonly="true" class="m-wrap large" size="20"    />
                        <form:hidden path="v_spd" id="v_spd" value="${v_spd}" /> 
                        <form:hidden path="peg_rekam" id="peg_rekam" value="${peg_rekam}" /> 
                        <form:hidden path="keterangan" id="keterangan" value="${keterangan}" /> 
                        <form:hidden path="idSpdSah" id="idSpdSah" value="${idSpdSah}" /> 
                    </div>
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SPD :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglSpd" path="tglSpd" id="tglSpd" readonly="true" class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="14" value=""/>
                     
                        <form:errors path="tglSpd" class="error" />
                    </div>
                </div>  
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SPD SAH :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text"  path="tglSpdSah" id="tglSpdSah" class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="14" value=""/>
                        <form:errors path="tglSpdSah" class="error" />
                    </div>
                </div>  
            </div>
           
            <div class="form-group">
                <label class="col-md-3 control-label">Alasan Batal :</label>
                <div class="col-md-5">
                    <div class="input-group">                      
                        <input path="alasanBatal" id="alasanBatal" name="alasanBatal" type="text"   class="m-wrap large " size="100"  />
                    </div>
                </div>
            </div>
                    
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Proses Batal </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/spd/pembatalanspdrev/pembatalanspdrev" >Kembali</a>
                </div>
            </div>        
                           
        </div>
    </div>
</div>

</form:form>
 <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/listbatalspd.js"></script>    

